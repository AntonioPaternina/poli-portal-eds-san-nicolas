package co.com.estacionsannicolas.repositorios;

import co.com.estacionsannicolas.model.LoginPersistido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Repository
public interface LoginPersistidoRepositorio extends JpaRepository<LoginPersistido, String> {

    LoginPersistido findByUsername(String username);

}
