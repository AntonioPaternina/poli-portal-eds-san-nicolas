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
    @OneToMany(mappedBy = "cliente")
    private List<PuntoRecompensa> puntosRecompensa;
    @OneToMany(mappedBy = "cliente")
    private List<SolicitudRedencion> solicitudesRedencion;

    public List<SolicitudRedencion> getSolicitudesRedencion() {
        return solicitudesRedencion;
    }

    public void setSolicitudesRedencion(List<SolicitudRedencion> solicitudesRedencion) {
        this.solicitudesRedencion = solicitudesRedencion;
    }

    public List<PuntoRecompensa> getPuntosRecompensa() {
        return puntosRecompensa;
    }

    public void setPuntosRecompensa(List<PuntoRecompensa> puntosRecompensa) {
        this.puntosRecompensa = puntosRecompensa;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

}
