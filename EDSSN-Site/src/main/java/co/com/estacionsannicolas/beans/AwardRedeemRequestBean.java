package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.RequestStatusEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AwardRedeemRequestBean.class)
public class AwardRedeemRequestBean extends BaseBean {

    private Date requestDate;

    private RequestStatusEnum status;

    private AwardBean award;

    private UserBean user;

    private MarketingCampaignBean marketingCampaign;

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

    public AwardBean getAward() {
        return award;
    }

    public void setAward(AwardBean award) {
        this.award = award;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public MarketingCampaignBean getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignBean marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }
}
