package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    @Autowired
    private UserService userService;

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<UserBean> getAll() {
        return userService.findAllCustomers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody UserBean user) {
        userService.create(user, RoleTypeEnum.CUSTOMER);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserBean getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserBean updateUser(@RequestBody UserBean user, @PathVariable Long id) {
        return userService.update(user);
    }

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody UserBean user, @PathVariable Long id) {
        userService.delete(user.getUsername());
    }
}
