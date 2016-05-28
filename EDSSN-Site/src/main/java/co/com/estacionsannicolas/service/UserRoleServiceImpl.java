package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserRoleBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import co.com.estacionsannicolas.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl extends BaseService implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRoleBean findByType(UserRoleTypeEnum roleType) {
        RoleEntity userRoleEntity = userRoleRepository.findByType(roleType);
        return mapper.map(userRoleEntity, UserRoleBean.class);
    }

}
