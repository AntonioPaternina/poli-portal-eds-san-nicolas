package co.com.estacionsannicolas.enums;

import java.io.Serializable;

public enum UserRoleTypeEnum implements Serializable {
    CUSTOMER("CUSTOMER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userRoleType;

    private UserRoleTypeEnum(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserProfileType() {
        return userRoleType;
    }

}
