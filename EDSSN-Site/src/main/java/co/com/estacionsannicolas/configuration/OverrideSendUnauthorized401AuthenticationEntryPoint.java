package co.com.estacionsannicolas.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverrideSendUnauthorized401AuthenticationEntryPoint implements AuthenticationEntryPoint {

    private Logger logger = LoggerFactory.getLogger(OverrideSendUnauthorized401AuthenticationEntryPoint.class);

    @Override
    public final void commence(HttpServletRequest request, HttpServletResponse response,
                               AuthenticationException authException) throws IOException {
        logger.debug("Overriding default Spring Security behaviour. Returning 401 instead of redirecting to login page");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}