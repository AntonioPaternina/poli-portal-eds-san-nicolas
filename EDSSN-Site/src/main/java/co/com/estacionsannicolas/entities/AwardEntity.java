package co.com.estacionsannicolas.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("A")
public class AwardEntity extends ProductEntity {

    private Long costInPoints;
    @ManyToMany
    private List<MarketingCampaignEntity> marketingCampaigns;
    @OneToMany(mappedBy = "award", fetch = FetchType.LAZY)
    private List<AwardRedeemRequestEntity> awardRequests;

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

    public List<AwardRedeemRequestEntity> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(List<AwardRedeemRequestEntity> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
