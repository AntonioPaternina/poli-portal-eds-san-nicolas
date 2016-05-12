package co.com.estacionsannicolas.repositorios;

import co.com.estacionsannicolas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<User, Integer> {

    User findBySsoId(String ssoId);

    void deleteBySsoId(String ssoId);

}
