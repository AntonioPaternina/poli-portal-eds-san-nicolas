package co.com.estacionsannicolas.controller;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import co.com.estacionsannicolas.service.UserService;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(UserBean user) {
        userService.createUser(user, UserRoleTypeEnum.CUSTOMER);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public UserBean getUser(Principal principal) {
        UserBean user = userService.findByUsername(principal.getName());
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public UserBean updateUser(UserBean user) {
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(UserBean user) {
        userService.deleteUser(user.getUsername());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserBean> getAll() {
        return userService.findAll();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
