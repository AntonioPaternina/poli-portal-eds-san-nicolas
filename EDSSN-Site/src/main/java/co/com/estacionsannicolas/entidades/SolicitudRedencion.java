package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa una solicitud de redención de puntos de recompensa
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class SolicitudRedencion extends EntidadBase implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;
    @ManyToOne
    private ProductoPremio productoPremio;
    @ManyToOne
    private UsuarioCliente cliente;

    public UsuarioCliente getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioCliente cliente) {
        this.cliente = cliente;
    }

    public ProductoPremio getProductoPremio() {
        return productoPremio;
    }

    public void setProductoPremio(ProductoPremio productoPremio) {
        this.productoPremio = productoPremio;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

}
