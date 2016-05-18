package co.com.estacionsannicolas.service.security;

import co.com.estacionsannicolas.service.BaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

public class AuthenticationSuccessHandlerImpl extends BaseService implements AuthenticationSuccessHandler {

    private final AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    public AuthenticationSuccessHandlerImpl(AuthenticationSuccessHandler defaultAuthenticationSuccessHandler) {
        this.defaultAuthenticationSuccessHandler = defaultAuthenticationSuccessHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (StringUtils.equals("true", (request.getHeader("X-Login-Ajax-call")))) {
            response.getWriter().print("ok");
            response.getWriter().flush();
        } else {
            defaultAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
