package co.com.estacionsannicolas.entities;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P")
@Table(name = "Award")
public class AwardEntity extends ProductEntity {

    private Long costInPoints;
    @ManyToMany
    private List<MarketingCampaignEntity> marketingCampaigns;
    @OneToMany(mappedBy = "award", fetch = FetchType.LAZY)
    private List<AwardRequestEntity> awardRequests;

    public Long getCostInPoints() {
        return costInPoints;
    }

    public void setCostInPoints(Long costInPoints) {
        this.costInPoints = costInPoints;
    }

    public List<MarketingCampaignEntity> getMarketingCampaigns() {
        return marketingCampaigns;
    }

    public void setMarketingCampaigns(List<MarketingCampaignEntity> marketingCampaigns) {
        this.marketingCampaigns = marketingCampaigns;
    }

    public List<AwardRequestEntity> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(List<AwardRequestEntity> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
