package co.com.estacionsannicolas.negocio.beans.registro;

import co.com.estacionsannicolas.servicio.entidades.Genero;
import co.com.estacionsannicolas.servicio.entidades.TelefonoContacto;
import co.com.estacionsannicolas.servicio.entidades.Vehiculo;
import java.util.Date;
import java.util.List;

/**
 * Componente que maneja el registro de nuevos usuarios
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class UsuarioClienteBean extends BaseBean {

    private String username;
    private String password;
    private String nombreCompleto;
    private String direccion;
    private String email;
    private List<TelefonoContacto> telefonos;
    private Genero sexo;
    private Date fechaNacimiento;
    private List<Vehiculo> vehiculos;

    /*
     * GETTERS & SETTERS 
     */
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

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
