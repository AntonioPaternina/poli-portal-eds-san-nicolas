package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.model.UserProfile;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.estacionsannicolas.repositorios.PerfilUsuarioRepositorio;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private PerfilUsuarioRepositorio perfilUsuarioRepositorio;

    @Override
    public UserProfile findById(int id) {
        return perfilUsuarioRepositorio.findOne(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return perfilUsuarioRepositorio.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return perfilUsuarioRepositorio.findAll();
    }
}
