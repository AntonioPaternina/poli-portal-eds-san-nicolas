package co.com.estacionsannicolas.config.hibernate;

import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta clase carga la unidad de persistencia cuando se inicializa el contexto
 * (y no cuando se hace la primera petición al servidor)
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@WebListener
public class RegistroUnidadPersistenciaListener
        implements ServletContextListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroUnidadPersistenciaListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.debug("Inicializando unidad de persistencia");
        Persistence.createEntityManagerFactory("edsapp");
        LOGGER.debug("Unidad de persistencia inicializada");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // TODO
        // cerrar los recursos cuando se acaba el contexto
    }
}
