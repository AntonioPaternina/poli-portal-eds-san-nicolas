package co.com.estacionsannicolas.modelo;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Componente que maneja el inicio de sesión
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Named
@RequestScoped
public class LoginBean {

    protected final Log logger = LogFactory.getLog(getClass());

    public void logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/logout");        
    }

}
