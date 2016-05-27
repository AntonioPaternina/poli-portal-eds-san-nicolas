package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.GenderEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Users")
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

    @Column
    private String address;

    @OneToMany(mappedBy = "user")
    private List<ContactNumberEntity> contactNumbers;
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
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VehicleEntity> vehicles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AwardPointEntity> awardPoints;
    @OneToMany(mappedBy = "user")
    private Set<AwardRequestEntity> awardRequests;

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

    public List<ContactNumberEntity> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<ContactNumberEntity> contactNumbers) {
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

    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleEntity> userRoles) {
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

    public Set<AwardRequestEntity> getAwardRequests() {
        return awardRequests;
    }

    public void setAwardRequests(Set<AwardRequestEntity> awardRequests) {
        this.awardRequests = awardRequests;
    }

}
