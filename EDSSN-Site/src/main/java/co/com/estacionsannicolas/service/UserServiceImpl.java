package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.beans.RoleBean;
import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.RoleTypeEnum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.estacionsannicolas.util.DozerHelper;
import co.com.estacionsannicolas.repositories.UserRepository;

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
        try {
            UserEntity userEntity = userEntityRepository.findByUsername(username);
            if (userEntity != null) {
                userBean = mapper.map(userEntity, UserBean.class);
            }
        } catch (Exception e) {
            logger.error("Error finding user with username: " + username);
        }
        return userBean;
    }

    @Override
    public void create(UserBean userBean, RoleTypeEnum roleType) {
        try {
            setUserRole(roleType, userBean);
            userBean.setPassword(passwordEncoder.encode(userBean.getPassword()));
            userBean.setAcive(true);

            UserEntity userEntity = mapper.map(userBean, UserEntity.class);

            setUserForVehicles(userEntity);
            initializeAwardPointsForTanquearSiPaga(userEntity, roleType);

            userEntityRepository.saveAndFlush(userEntity);
        } catch (Exception e) {
            logger.error("Error creating user", e);
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
        try {
            UserEntity userToSave = mapper.map(userBean, UserEntity.class);
            userToSave = userEntityRepository.saveAndFlush(userToSave);
            savedUser = mapper.map(userToSave, UserBean.class);
        } catch (Exception e) {
            logger.error("Error saving user {}", userBean, e);
        }
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
        try {
            RoleEntity customerRole = mapper.map(roleService.findByType(RoleTypeEnum.CUSTOMER), RoleEntity.class);
            customers = DozerHelper.map(mapper, userEntityRepository.findByUserRoles(customerRole), UserBean.class);
        } catch (Exception e) {
            logger.error("Error retrieving customers", e);
        }
        return customers;
    }

    @Override
    public boolean isUsernameUnique(Long idUsuario, String ssoId) {
        UserBean usuarioPersistido = findByUsername(ssoId);
        return (usuarioPersistido == null || ((idUsuario != null) && (usuarioPersistido.getId().compareTo(idUsuario) == 0)));
    }

}
