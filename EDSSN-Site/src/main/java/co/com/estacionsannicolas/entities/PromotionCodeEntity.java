package co.com.estacionsannicolas.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "PROMOTION_CODE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")})
public class PromotionCodeEntity extends BaseEntity implements Serializable {

    private String code;

    private Boolean used = false;

    private Long points;

    @ManyToOne
    private MarketingCampaignEntity marketingCampaign;

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

    public MarketingCampaignEntity getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignEntity marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

}
