package co.com.estacionsannicolas.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Clase que registra la configuración de seguridad de Spring Security
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class InicializadorSpringSecurity extends AbstractSecurityWebApplicationInitializer {

    public InicializadorSpringSecurity() {
        super(ConfiguracionSpringSecurity.class);
    }
}
