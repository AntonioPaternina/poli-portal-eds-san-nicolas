package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.RequestStatusEnum;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AwardRequest")
public class AwardRequestEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum status;
    @ManyToOne
    private AwardEntity award;
    @ManyToOne
    private UserEntity user;

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

}
