package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.RoleTypeEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = RoleBean.class)
public class RoleBean extends BaseBean {

    private RoleTypeEnum type;

    public RoleTypeEnum getType() {
        return type;
    }

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }

}
