package co.com.estacionsannicolas.servicio.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class RolUsuario extends EntidadBase implements Serializable {

    private String nombre;
    @ManyToOne
    private Usuario usuario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
