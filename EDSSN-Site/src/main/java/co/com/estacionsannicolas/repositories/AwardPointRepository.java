package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardPointRepository extends JpaRepository<AwardPointEntity, Long> {

    List<AwardPointEntity> findByUser(UserEntity user);
}
