package co.com.estacionsannicolas.config;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Configuration
public class AppConfig {

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/edsapp");
        } catch (NamingException e) {
        }
        return dataSource;
    }

}
