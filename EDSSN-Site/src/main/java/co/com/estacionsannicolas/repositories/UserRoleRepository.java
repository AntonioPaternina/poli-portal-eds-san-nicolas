package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.UserRoleEntity;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

    UserRoleEntity findByType(UserRoleTypeEnum type);
}
