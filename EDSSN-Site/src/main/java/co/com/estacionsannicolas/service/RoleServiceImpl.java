package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.RoleBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleBean findByType(RoleTypeEnum roleType) {
        RoleEntity userRoleEntity = roleRepository.findByType(roleType);
        return mapper.map(userRoleEntity, RoleBean.class);
    }

}
