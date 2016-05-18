package co.com.estacionsannicolas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MarketingCampaign")
public class MarketingCampaignEntity extends BaseEntity implements Serializable {

    private String name;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @ManyToMany(mappedBy = "marketingCampaigns")
    private List<AwardEntity> awards;
    @OneToMany(mappedBy = "marketingCampaign", fetch = FetchType.LAZY)
    private List<AwardPointEntity> awardPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<AwardEntity> getAwards() {
        return awards;
    }

    public void setAwards(List<AwardEntity> awards) {
        this.awards = awards;
    }

    public List<AwardPointEntity> getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(List<AwardPointEntity> awardPoints) {
        this.awardPoints = awardPoints;
    }

}
