package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.beans.RoleBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.repositories.UserRepository;
import co.com.estacionsannicolas.service.exceptions.UsernameIsNotUniqueException;
import co.com.estacionsannicolas.util.DozerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userEntityRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MarketingCampaignService marketingCampaignService;

    @Override
    public UserBean findById(Long id) {
        UserEntity userEntity = userEntityRepository.findOne(id);
        return mapper.map(userEntity, UserBean.class);
    }

    @Override
    public UserBean findByUsername(String username) {
        UserBean userBean = null;

        UserEntity userEntity = userEntityRepository.findByUsername(username);
        if (userEntity != null) {
            userBean = mapper.map(userEntity, UserBean.class);
        }

        return userBean;
    }

    @Override
    public UserBean create(UserBean userBean, RoleTypeEnum roleType) throws UsernameIsNotUniqueException {
        processValidations(userBean);

        UserBean createdUser = null;
        setUserRole(roleType, userBean);
        userBean.setPassword(passwordEncoder.encode(userBean.getPassword()));
        userBean.setAcive(true);

        UserEntity userEntity = mapper.map(userBean, UserEntity.class);

        setUserForVehicles(userEntity);
        initializeAwardPointsForTanquearSiPaga(userEntity, roleType);

        createdUser = mapper.map(userEntityRepository.saveAndFlush(userEntity), UserBean.class);
        return createdUser;
    }

    private void processValidations(UserBean userBean) throws UsernameIsNotUniqueException {
        if (!isUsernameUnique(userBean.getId(), userBean.getUsername())) {
            throw new UsernameIsNotUniqueException();
        }
    }

    private void setUserForVehicles(UserEntity userEntity) {
        if (userEntity.getVehicles() != null) {
            userEntity.getVehicles().stream().forEach((vehicle) -> {
                vehicle.setUser(userEntity);
            });
        }
    }

    private void initializeAwardPointsForTanquearSiPaga(UserEntity userEntity, RoleTypeEnum roleType) {
        if (RoleTypeEnum.CUSTOMER.equals(roleType)) {
            MarketingCampaignBean tanquearSiPagaCampaign = marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());

            AwardPointEntity tanquearSiPagaPoints = new AwardPointEntity();
            tanquearSiPagaPoints.setMarketingCampaign(mapper.map(tanquearSiPagaCampaign, MarketingCampaignEntity.class));
            tanquearSiPagaPoints.setNumberOfPoints(0L);

            userEntity.addAwardPoint(tanquearSiPagaPoints);
        }
    }

    private void setUserRole(RoleTypeEnum roleType, UserBean userBean) {
        RoleBean customerRole = roleService.findByType(roleType);
        userBean.getUserRoles().add(customerRole);
    }

    @Override
    public UserBean update(UserBean userBean) {
        UserBean savedUser = null;

        UserEntity userToSave = mapper.map(userBean, UserEntity.class);
        userToSave = userEntityRepository.saveAndFlush(userToSave);
        savedUser = mapper.map(userToSave, UserBean.class);

        return savedUser;
    }

    @Override
    public void delete(String ssoId) {
        userEntityRepository.deleteByUsername(ssoId);
    }

    @Override
    public List<UserBean> findAll() {
        List<UserEntity> users = userEntityRepository.findAll();
        return DozerHelper.map(mapper, users, UserBean.class);
    }

    @Override
    public List<UserBean> findAllCustomers() {
        List<UserBean> customers = null;
        RoleEntity customerRole = mapper.map(roleService.findByType(RoleTypeEnum.CUSTOMER), RoleEntity.class);
        customers = DozerHelper.map(mapper, userEntityRepository.findByUserRoles(customerRole), UserBean.class);

        return customers;
    }

    @Override
    public boolean isUsernameUnique(Long idUsuario, String username) {
        UserBean usuarioPersistido = findByUsername(username);
        return (usuarioPersistido == null || ((idUsuario != null) && (usuarioPersistido.getId().compareTo(idUsuario) == 0)));
    }

}
