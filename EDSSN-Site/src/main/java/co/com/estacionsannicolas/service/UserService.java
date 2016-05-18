package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;

public interface UserService {

    UserBean findById(Long id);

    UserBean findByUsername(String username);

    void createUser(UserBean user, UserRoleTypeEnum roleType);

    UserBean updateUser(UserBean user);

    void deleteUser(String username);

    List<UserBean> findAll();

    boolean isUsernameUnique(Long id, String username);

}
