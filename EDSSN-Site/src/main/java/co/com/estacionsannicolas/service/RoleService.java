package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.RoleBean;
import co.com.estacionsannicolas.enums.RoleTypeEnum;

public interface RoleService {

    RoleBean findByType(RoleTypeEnum roleType);
}
