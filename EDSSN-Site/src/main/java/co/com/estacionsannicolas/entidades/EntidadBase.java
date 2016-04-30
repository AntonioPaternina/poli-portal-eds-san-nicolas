package co.com.estacionsannicolas.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Es la entidad base
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@MappedSuperclass
public class EntidadBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
