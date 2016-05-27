package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.beans.UserRoleBean;
import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
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
    private UserRoleService userRoleService;

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
    public void create(UserBean userBean, UserRoleTypeEnum roleType) {
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

    private void initializeAwardPointsForTanquearSiPaga(UserEntity userEntity, UserRoleTypeEnum roleType) {
        if (UserRoleTypeEnum.CUSTOMER.equals(roleType)) {
            MarketingCampaignBean tanquearSiPagaCampaign = marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());

            AwardPointEntity tanquearSiPagaPoints = new AwardPointEntity();
            tanquearSiPagaPoints.setMarketingCampaign(mapper.map(tanquearSiPagaCampaign, MarketingCampaignEntity.class));
            tanquearSiPagaPoints.setNumberOfPoints(0L);

            userEntity.addAwardPoint(tanquearSiPagaPoints);
        }
    }

    private void setUserRole(UserRoleTypeEnum roleType, UserBean userBean) {
        UserRoleBean customerRole = userRoleService.findByType(roleType);
        userBean.getUserRoles().add(customerRole);
    }

    @Override
    public UserBean update(UserBean userBean) {
        UserEntity savedUser = userEntityRepository.findOne(userBean.getId());
        UserEntity userToSave = mapper.map(userBean, UserEntity.class);
        if (savedUser != null) {
            savedUser.setUsername(userToSave.getUsername());
            if (!userToSave.getPassword().equals(savedUser.getPassword())) {
                savedUser.setPassword(passwordEncoder.encode(userToSave.getPassword()));
            }
            savedUser.setFullName(userToSave.getFullName());
            savedUser.setEmail(userToSave.getEmail());
            savedUser.setUserRoles(userToSave.getUserRoles());

            savedUser = userEntityRepository.saveAndFlush(savedUser);
        }
        return mapper.map(savedUser, UserBean.class);
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
    public boolean isUsernameUnique(Long idUsuario, String ssoId) {
        UserBean usuarioPersistido = findByUsername(ssoId);
        return (usuarioPersistido == null || ((idUsuario != null) && (usuarioPersistido.getId().compareTo(idUsuario) == 0)));
    }

}
