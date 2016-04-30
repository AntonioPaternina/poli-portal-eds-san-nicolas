package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Telefonos de contacto
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class TelefonoContacto extends EntidadBase implements Serializable {

    @ManyToOne
    private Usuario usuario;
    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
