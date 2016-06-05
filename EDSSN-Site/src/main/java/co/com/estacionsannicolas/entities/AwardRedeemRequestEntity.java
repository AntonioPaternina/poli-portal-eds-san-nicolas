package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.RequestStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AWARD_REDEEM_REQUEST")
public class AwardRedeemRequestEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum status;
    @ManyToOne
    private AwardEntity award;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private MarketingCampaignEntity marketingCampaign;

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public RequestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RequestStatusEnum status) {
        this.status = status;
    }

    public AwardEntity getAward() {
        return award;
    }

    public void setAward(AwardEntity award) {
        this.award = award;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MarketingCampaignEntity getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignEntity marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }
}
