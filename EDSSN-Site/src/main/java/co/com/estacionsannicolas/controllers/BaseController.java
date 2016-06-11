package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.ErrorBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.service.UserService;
import co.com.estacionsannicolas.service.exceptions.EdssnServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    private UserService userService;

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

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorBean> genericErrorHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error General"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EdssnServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorBean> serviceErrorHandler(EdssnServiceException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
