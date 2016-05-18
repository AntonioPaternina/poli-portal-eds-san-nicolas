package co.com.estacionsannicolas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AwardPoint")
public class AwardPointEntity extends BaseEntity implements Serializable {

    private Long numberOfPoints;
    @ManyToOne
    private MarketingCampaignEntity marketingCampaign;
    @ManyToOne
    private UserEntity user;

    public Long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public MarketingCampaignEntity getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignEntity marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
