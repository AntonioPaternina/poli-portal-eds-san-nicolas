package co.com.estacionsannicolas.repositorios;

import co.com.estacionsannicolas.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepositorio extends JpaRepository<UserProfile, Integer> {

    UserProfile findByType(String type);
}
