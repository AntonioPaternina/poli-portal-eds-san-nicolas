package co.com.estacionsannicolas.config.security;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

/**
 * Esta clase configura los beans de Spring que se necesiten.
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Configuration
public class ConfiguracionSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionSpring.class);

    /**
     * Este método instancia un bean de tipo DataSource provisto por el entorno
     * (tomcat)
     *
     * @return un bean de tipo DataSource
     */
    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/edsapp");
        } catch (NamingException e) {
            LOGGER.error("Ocurrió un error recuperando DataSource usando JNDI");
        }
        return dataSource;
    }

}
