package co.com.estacionsannicolas.servicio;

import co.com.estacionsannicolas.negocio.beans.registro.UsuarioClienteBean;

/**
 * Servicio para gestión de usuarios
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public interface UsuariosServicio {

    UsuarioClienteBean registrarUsuarioNuevo(UsuarioClienteBean nuevoCliente);

    UsuarioClienteBean actualizarUsuarioNuevo(UsuarioClienteBean cliente);
}
