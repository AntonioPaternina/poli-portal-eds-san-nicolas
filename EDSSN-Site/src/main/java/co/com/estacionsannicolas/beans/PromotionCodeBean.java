package co.com.estacionsannicolas.beans;

public class PromotionCodeBean extends BaseBean {

    private String code;

    private Boolean used;

    private Long points;

    private MarketingCampaignBean marketingCampaign;

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public MarketingCampaignBean getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignBean marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

}
