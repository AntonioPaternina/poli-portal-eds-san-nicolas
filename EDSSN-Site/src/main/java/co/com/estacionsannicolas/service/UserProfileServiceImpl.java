package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.estacionsannicolas.repositories.UserRoleRepository;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl extends BaseService implements UserProfileService {

    @Autowired
    private UserRoleRepository perfilUsuarioRepositorio;

    @Override
    public RoleEntity findById(int id) {
        return perfilUsuarioRepositorio.findOne(id);
    }

    @Override
    public RoleEntity findByType(UserRoleTypeEnum type) {
        return perfilUsuarioRepositorio.findByType(type);
    }

    @Override
    public List<RoleEntity> findAll() {
        return perfilUsuarioRepositorio.findAll();
    }
}
