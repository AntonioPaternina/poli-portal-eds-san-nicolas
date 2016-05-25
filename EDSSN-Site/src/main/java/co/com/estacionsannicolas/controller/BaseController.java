package co.com.estacionsannicolas.controller;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;

    @Autowired
    protected MessageSource messageSource;

    protected boolean isUserAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    protected String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    protected UserBean getCurrentUser() {
        return userService.findByUsername(getPrincipal());
    }
}
