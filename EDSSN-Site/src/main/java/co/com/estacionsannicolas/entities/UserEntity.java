package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.GenderEnum;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class UserEntity extends BaseEntity implements Serializable {

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String nationalId;

    @NotEmpty
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "active")
    private boolean acive;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cellphone")
    private String cellphone;

    @Enumerated
    private GenderEnum gender;
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_role_id")})
    private Set<RoleEntity> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VehicleEntity> vehicles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AwardPointEntity> awardPoints;
    @OneToMany(mappedBy = "user")
    private Set<AwardRedeemRequestEntity> awardRequests;

    public void addAwardPoint(AwardPointEntity awardPoint) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public Set<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<AwardPointEntity> getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(Set<AwardPointEntity> awardPoints) {
        this.awardPoints = awardPoints;
    }

    public Set<AwardRedeemRequestEntity> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(Set<AwardRedeemRequestEntity> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
