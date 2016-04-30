package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Representa la cantidad de puntos de recompensa de un cliente
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class PuntoRecompensa extends EntidadBase implements Serializable {

    private Long cantidadPuntos;

    public Long getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(Long cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

}
