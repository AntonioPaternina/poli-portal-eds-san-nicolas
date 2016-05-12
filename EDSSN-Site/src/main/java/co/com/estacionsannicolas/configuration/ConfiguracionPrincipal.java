package co.com.estacionsannicolas.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Clase de configuración principal que reúne todas las configuraciones.
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Configuration
@Import(value = {
    ConfiguracionPersistencia.class,
    ConfiguracionSpringMvc.class,
    ConfiguracionSpringSecurity.class
})
public class ConfiguracionPrincipal {

}
