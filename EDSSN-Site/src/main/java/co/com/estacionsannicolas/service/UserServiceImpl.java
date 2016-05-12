package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.model.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.estacionsannicolas.repositorios.UsuarioRepositorio;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User buscarPorId(int id) {
        return usuarioRepositorio.findOne(id);
    }

    @Override
    public User buscarPorSsoId(String ssoId) {
        User user = usuarioRepositorio.findBySsoId(ssoId);
        return user;
    }

    @Override
    public void crearUsuario(User usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void actualizarUsuario(User usuarioPorGuardar) {
        User usuarioPersistido = usuarioRepositorio.findOne(usuarioPorGuardar.getId());
        if (usuarioPersistido != null) {
            usuarioPersistido.setSsoId(usuarioPorGuardar.getSsoId());
            if (!usuarioPorGuardar.getPassword().equals(usuarioPersistido.getPassword())) {
                usuarioPersistido.setPassword(passwordEncoder.encode(usuarioPorGuardar.getPassword()));
            }
            usuarioPersistido.setFirstName(usuarioPorGuardar.getFirstName());
            usuarioPersistido.setLastName(usuarioPorGuardar.getLastName());
            usuarioPersistido.setEmail(usuarioPorGuardar.getEmail());
            usuarioPersistido.setUserProfiles(usuarioPorGuardar.getUserProfiles());
        }
    }

    @Override
    public void eliminarPorSsoId(String ssoId) {
        usuarioRepositorio.deleteBySsoId(ssoId);
    }

    @Override
    public List<User> buscarTodos() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public boolean isSsoIdUnico(Integer idUsuario, String ssoId) {
        User usuarioPersistido = buscarPorSsoId(ssoId);
        return (usuarioPersistido == null || ((idUsuario != null) && (usuarioPersistido.getId().compareTo(idUsuario) == 0)));
    }

}
