package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Telefonos de contacto
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class TelefonoContacto extends EntidadBase implements Serializable {

    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
