package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
