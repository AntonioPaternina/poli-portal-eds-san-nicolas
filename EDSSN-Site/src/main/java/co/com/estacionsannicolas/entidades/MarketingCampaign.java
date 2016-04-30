package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa una campaña de mercadeo
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class MarketingCampaign extends EntidadBase implements Serializable {

    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @ManyToMany(mappedBy = "marketingCampaigns")
    private List<ProductoPremio> premios;
    @OneToMany(mappedBy = "marketingCampaign", fetch = FetchType.LAZY)
    private List<PuntoRecompensa> puntosRecompensa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<ProductoPremio> getPremios() {
        return premios;
    }

    public void setPremios(List<ProductoPremio> premios) {
        this.premios = premios;
    }

    public List<PuntoRecompensa> getPuntosRecompensa() {
        return puntosRecompensa;
    }

    public void setPuntosRecompensa(List<PuntoRecompensa> puntosRecompensa) {
        this.puntosRecompensa = puntosRecompensa;
    }

}
