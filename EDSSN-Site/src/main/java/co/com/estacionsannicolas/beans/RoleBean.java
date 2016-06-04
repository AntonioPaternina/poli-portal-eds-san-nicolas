package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.RoleTypeEnum;

public class RoleBean extends BaseBean {

    private RoleTypeEnum type;

    public RoleTypeEnum getType() {
        return type;
    }

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }

}
