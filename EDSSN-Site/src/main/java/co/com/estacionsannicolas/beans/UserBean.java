package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserBean extends BaseBean {

    private String nationalId;
    private String username;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private String fullName;
    private String email;
    private boolean acive;
    private String address;
    private List<ContactNumberBean> contactNumbers;
    private GenderEnum gender;
    private Date birthdate;
    @JsonProperty(access = Access.READ_ONLY)
    private Set<RoleBean> userRoles = new HashSet<>();
    private Set<VehicleBean> vehicles;
    @JsonProperty(access = Access.READ_ONLY)
    private Set<AwardPointBean> awardPoints;
    @JsonIgnore
    private Set<AwardRequestBean> awardRequests;

    public void addAwardPoint(AwardPointBean awardPoint) {
        if (awardPoints == null) {
            awardPoints = new HashSet<>();
        }

        awardPoint.setUser(this);
        awardPoints.add(awardPoint);
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAcive() {
        return acive;
    }

    public void setAcive(boolean acive) {
        this.acive = acive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContactNumberBean> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<ContactNumberBean> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Set<RoleBean> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleBean> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<VehicleBean> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleBean> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<AwardPointBean> getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(Set<AwardPointBean> awardPoints) {
        this.awardPoints = awardPoints;
    }

    public Set<AwardRequestBean> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(Set<AwardRequestBean> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
