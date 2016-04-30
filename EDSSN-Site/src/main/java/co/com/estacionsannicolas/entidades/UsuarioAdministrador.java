package co.com.estacionsannicolas.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representa un usuario administrador del sitema
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
@DiscriminatorValue("A")
public class UsuarioAdministrador extends Usuario {

}
