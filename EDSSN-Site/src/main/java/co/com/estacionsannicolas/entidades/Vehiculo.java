package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Representa un vehiculo de un cliente
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class Vehiculo extends EntidadBase implements Serializable {

    @ManyToOne
    private UsuarioCliente cliente;
}
