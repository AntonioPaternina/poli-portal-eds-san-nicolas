package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;

public interface UserService {

    UserBean findById(Long id);

    UserBean findByUsername(String username);

    void create(UserBean user, UserRoleTypeEnum roleType);

    UserBean update(UserBean user);

    void delete(String username);

    List<UserBean> findAll();

    boolean isUsernameUnique(Long id, String username);

}
