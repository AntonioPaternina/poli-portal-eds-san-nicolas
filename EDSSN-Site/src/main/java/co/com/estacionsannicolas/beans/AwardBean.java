package co.com.estacionsannicolas.beans;

import java.util.List;

public class AwardBean extends ProductBean {

    private Long costInPoints;

    private List<MarketingCampaignBean> marketingCampaigns;

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
