package co.com.estacionsannicolas.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class AwardBean extends ProductBean {

    private Long costInPoints;

    @JsonIgnore
    private List<MarketingCampaignBean> marketingCampaigns;

    @JsonIgnore
    private List<AwardRequestBean> awardRequests;

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

    public List<AwardRequestBean> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(List<AwardRequestBean> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
