package co.com.estacionsannicolas.beans;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AwardBean.class)
public class AwardBean extends ProductBean {

    private Long costInPoints;

    private List<MarketingCampaignBean> marketingCampaigns;

    @JsonIgnore
    private List<AwardRedeemRequestBean> awardRequests;

    public Long getCostInPoints() {
        return costInPoints;
    }

    public void setCostInPoints(Long costInPoints) {
        this.costInPoints = costInPoints;
    }

    public List<MarketingCampaignBean> getMarketingCampaigns() {
        return marketingCampaigns;
    }

    public void setMarketingCampaigns(List<MarketingCampaignBean> marketingCampaigns) {
        this.marketingCampaigns = marketingCampaigns;
    }

    public List<AwardRedeemRequestBean> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(List<AwardRedeemRequestBean> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
