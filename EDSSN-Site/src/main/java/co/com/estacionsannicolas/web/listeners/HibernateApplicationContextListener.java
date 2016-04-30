package co.com.estacionsannicolas.web.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@WebListener
public class HibernateApplicationContextListener
        implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("edsapp");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
