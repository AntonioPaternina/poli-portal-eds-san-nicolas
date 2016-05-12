package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.model.User;
import java.util.List;

public interface UserService {

    User buscarPorId(int id);

    User buscarPorSsoId(String sso);

    void crearUsuario(User user);

    void actualizarUsuario(User user);

    void eliminarPorSsoId(String sso);

    List<User> buscarTodos();

    boolean isSsoIdUnico(Integer id, String sso);

}
