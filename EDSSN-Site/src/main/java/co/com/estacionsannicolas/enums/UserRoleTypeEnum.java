package co.com.estacionsannicolas.enums;

import co.com.estacionsannicolas.entities.RoleEntity;
import java.io.Serializable;

public enum UserRoleTypeEnum implements Serializable {
    CUSTOMER(RoleEntity.CUSTOMER),
    ADMIN(RoleEntity.ADMIN);

    String userRoleType;

    private UserRoleTypeEnum(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserProfileType() {
        return userRoleType;
    }

}
