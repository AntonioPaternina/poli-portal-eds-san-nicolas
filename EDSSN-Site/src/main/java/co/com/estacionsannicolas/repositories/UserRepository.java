package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    void deleteByUsername(String username);

    List<UserEntity> findByUserRoles(RoleEntity userRole);

}
