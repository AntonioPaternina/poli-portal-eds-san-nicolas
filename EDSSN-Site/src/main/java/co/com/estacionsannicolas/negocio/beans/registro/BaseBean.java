package co.com.estacionsannicolas.negocio.beans.registro;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entidad base para los beans manejados por JSF
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public abstract class BaseBean implements Serializable {

    protected final Logger Logger = LoggerFactory.getLogger(this.getClass());
}
