package co.com.estacionsannicolas.servicio.impl;

import co.com.estacionsannicolas.negocio.beans.registro.UsuarioClienteBean;
import co.com.estacionsannicolas.servicio.UsuariosServicio;
import javax.ejb.EJB;

/**
 * Implementación de servicio de gestión de usuarios
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@EJB
public class UsuariosServicioImpl extends BaseServicio implements UsuariosServicio {

    @Override
    public UsuarioClienteBean registrarUsuarioNuevo(UsuarioClienteBean nuevoCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioClienteBean actualizarUsuarioNuevo(UsuarioClienteBean cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
