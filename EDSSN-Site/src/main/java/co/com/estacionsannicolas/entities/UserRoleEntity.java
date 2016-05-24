package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "UserRole")
public class UserRoleEntity extends BaseEntity implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String CUSTOMER = "CUSTOMER";

    @Column(length = 15, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleTypeEnum type;

    public UserRoleTypeEnum getType() {
        return type;
    }

    public void setType(UserRoleTypeEnum type) {
        this.type = type;
    }

}
