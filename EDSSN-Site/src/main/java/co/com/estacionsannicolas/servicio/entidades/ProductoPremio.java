package co.com.estacionsannicolas.servicio.entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Representa un producto que está diponible como premio de una campaña de
 * marketing
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
@DiscriminatorValue("P")
public class ProductoPremio extends Producto {

    private Long costoEnPuntos;
    @ManyToMany
    private List<MarketingCampaign> marketingCampaigns;
    @OneToMany(mappedBy = "productoPremio", fetch = FetchType.LAZY)
    private List<SolicitudRedencion> solicitudesRedencion;

    public List<SolicitudRedencion> getSolicitudesRedencion() {
        return solicitudesRedencion;
    }

    public void setSolicitudesRedencion(List<SolicitudRedencion> solicitudesRedencion) {
        this.solicitudesRedencion = solicitudesRedencion;
    }

    public Long getCostoEnPuntos() {
        return costoEnPuntos;
    }

    public void setCostoEnPuntos(Long costoEnPuntos) {
        this.costoEnPuntos = costoEnPuntos;
    }

    public List<MarketingCampaign> getMarketingCampaigns() {
        return marketingCampaigns;
    }

    public void setMarketingCampaigns(List<MarketingCampaign> marketingCampaigns) {
        this.marketingCampaigns = marketingCampaigns;
    }

}
