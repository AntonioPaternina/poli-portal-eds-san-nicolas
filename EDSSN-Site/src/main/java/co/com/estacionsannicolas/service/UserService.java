package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.service.exceptions.UsernameIsNotUniqueException;

import java.util.List;

public interface UserService {

    UserBean findById(Long id);

    UserBean findByUsername(String username);

    UserBean create(UserBean user, RoleTypeEnum roleType) throws UsernameIsNotUniqueException;

    UserBean update(UserBean user);

    void delete(String username);

    List<UserBean> findAll();

    List<UserBean> findAllCustomers();

    boolean isUsernameUnique(Long id, String username);

}
