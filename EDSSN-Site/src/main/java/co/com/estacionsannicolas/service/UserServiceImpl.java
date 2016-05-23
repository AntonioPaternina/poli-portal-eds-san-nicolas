package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import co.com.estacionsannicolas.entities.UserRoleEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.estacionsannicolas.util.DozerHelper;
import co.com.estacionsannicolas.repositories.UserRepository;
import co.com.estacionsannicolas.repositories.UserRoleRepository;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userEntityRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
        UserEntity userEntity = mapper.map(userBean, UserEntity.class);
        UserRoleEntity customerRole = userRoleRepository.findByType(roleType);
        userEntity.getUserRoles().add(customerRole);
        userEntity.setPassword(passwordEncoder.encode(userBean.getPassword()));
        userEntity.setAcive(true);
        
        MarketingCampaignEntity tanquearSiPagaCampaign = marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());
        // TODO create award point for this default campaign
        
        userEntityRepository.save(userEntity);
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
