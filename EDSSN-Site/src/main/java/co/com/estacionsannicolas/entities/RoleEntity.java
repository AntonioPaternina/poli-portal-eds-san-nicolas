package co.com.estacionsannicolas.entities;

import co.com.estacionsannicolas.enums.RoleTypeEnum;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class RoleEntity extends BaseEntity implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String CUSTOMER = "CUSTOMER";

    @Column(length = 15, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;

    public RoleTypeEnum getType() {
        return type;
    }

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }

}
