package co.com.estacionsannicolas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa un usuario que utiliza el sistema
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipoUsuario")
public abstract class Usuario extends EntidadBase implements Serializable {

    private String username;
    private String password;
    private String nombreCompleto;
    private String direccion;
    @OneToMany
    private List<TelefonoContacto> telefonos;
    @Enumerated(EnumType.STRING)
    private Genero sexo;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<TelefonoContacto> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoContacto> telefonos) {
        this.telefonos = telefonos;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
