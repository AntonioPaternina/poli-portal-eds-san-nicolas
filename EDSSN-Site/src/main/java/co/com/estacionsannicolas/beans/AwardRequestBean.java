package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.RequestStatusEnum;
import java.util.Date;

public class AwardRequestBean extends BaseBean {

    private Date requestDate;

    private RequestStatusEnum status;

    private AwardBean award;

    private UserBean user;

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

}
