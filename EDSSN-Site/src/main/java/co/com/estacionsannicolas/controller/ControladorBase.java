package co.com.estacionsannicolas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class ControladorBase {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * This method returns true if users is already authenticated [logged-in],
     * else false.
     */
    protected boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
