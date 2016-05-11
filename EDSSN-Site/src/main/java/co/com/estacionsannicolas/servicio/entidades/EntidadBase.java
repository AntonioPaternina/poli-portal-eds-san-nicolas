package co.com.estacionsannicolas.servicio.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Es la entidad base
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@MappedSuperclass
public class EntidadBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
