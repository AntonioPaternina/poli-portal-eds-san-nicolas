package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserRoleBean;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;

public interface UserRoleService {

    UserRoleBean findByType(UserRoleTypeEnum roleType);
}
