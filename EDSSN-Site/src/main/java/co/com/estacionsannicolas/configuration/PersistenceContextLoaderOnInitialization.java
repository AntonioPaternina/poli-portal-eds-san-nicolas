package co.com.estacionsannicolas.configuration;

import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.stereotype.Service;

@WebListener
@Service
public class PersistenceContextLoaderOnInitialization
        implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Persistence.createEntityManagerFactory("edsapp");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // TODO cerrar los recursos cuando se acaba el contexto
    }
}
