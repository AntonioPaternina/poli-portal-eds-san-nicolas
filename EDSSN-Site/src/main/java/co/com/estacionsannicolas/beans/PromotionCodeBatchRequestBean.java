package co.com.estacionsannicolas.beans;

public class PromotionCodeBatchRequestBean extends BaseBean {

    private long numberOfCodesToCreate;
    private long awardPointsPercode;
    private int codeLength;
    private MarketingCampaignBean marketingCampaign;

    public long getNumberOfCodesToCreate() {
        return numberOfCodesToCreate;
    }

    public void setNumberOfCodesToCreate(long numberOfCodesToCreate) {
        this.numberOfCodesToCreate = numberOfCodesToCreate;
    }

    public long getAwardPointsPercode() {
        return awardPointsPercode;
    }

    public void setAwardPointsPercode(long awardPointsPercode) {
        this.awardPointsPercode = awardPointsPercode;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public MarketingCampaignBean getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignBean marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

}
