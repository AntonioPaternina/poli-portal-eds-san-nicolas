package co.com.estacionsannicolas.enums;

import co.com.estacionsannicolas.entities.UserRoleEntity;
import java.io.Serializable;

public enum UserRoleTypeEnum implements Serializable {
    CUSTOMER(UserRoleEntity.CUSTOMER),
    ADMIN(UserRoleEntity.ADMIN);

    String userRoleType;

    private UserRoleTypeEnum(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserProfileType() {
        return userRoleType;
    }

}
