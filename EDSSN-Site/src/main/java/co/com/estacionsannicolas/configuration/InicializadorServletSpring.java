package co.com.estacionsannicolas.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Esta clase Configura un DispatcherServlet para Spring
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class InicializadorServletSpring extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ConfiguracionPrincipal.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
