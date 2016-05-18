package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.UserRoleTypeEnum;

public class UserRoleBean extends BaseBean {

    private UserRoleTypeEnum type;

    public UserRoleTypeEnum getType() {
        return type;
    }

    public void setType(UserRoleTypeEnum type) {
        this.type = type;
    }

}
