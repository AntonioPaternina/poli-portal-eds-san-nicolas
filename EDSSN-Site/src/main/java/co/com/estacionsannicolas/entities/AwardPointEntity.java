package co.com.estacionsannicolas.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "AWARD_POINT")
public class AwardPointEntity extends BaseEntity implements Serializable {

    private Long numberOfPoints;
    @ManyToOne
    private MarketingCampaignEntity marketingCampaign;
    @ManyToOne
    private UserEntity user;

    public void decreaseNumberOfPoints(Long pointsToDecrease) {
        setNumberOfPoints(getNumberOfPoints() - pointsToDecrease);
    }

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
