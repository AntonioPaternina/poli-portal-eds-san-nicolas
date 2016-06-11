package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.PersistentLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistentLoginRepository extends JpaRepository<PersistentLoginEntity, String> {

    PersistentLoginEntity findByUsername(String username);

    PersistentLoginEntity findBySeries(String series);

}
