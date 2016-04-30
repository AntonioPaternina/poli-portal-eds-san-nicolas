package co.com.estacionsannicolas.entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Representa un usuario que es cliente de la estación de servicio
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
@DiscriminatorValue("C")
public class UsuarioCliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private List<Vehiculo> vehiculos;

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

}
