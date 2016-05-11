package co.com.estacionsannicolas.servicio.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Representa la cantidad de puntos de recompensa de un cliente
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class PuntoRecompensa extends EntidadBase implements Serializable {

    private Long cantidadPuntos;
    @ManyToOne
    private MarketingCampaign marketingCampaign;
    @ManyToOne
    private UsuarioCliente cliente;

    public UsuarioCliente getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioCliente cliente) {
        this.cliente = cliente;
    }

    public Long getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(Long cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

    public MarketingCampaign getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaign marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

}
