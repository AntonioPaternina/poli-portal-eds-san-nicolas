package co.com.estacionsannicolas.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AwardPointBean extends BaseBean {

    private Long numberOfPoints;

    private MarketingCampaignBean marketingCampaign;

    @JsonIgnore
    private UserBean user;

    public Long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public MarketingCampaignBean getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignBean marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
