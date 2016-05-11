package co.com.estacionsannicolas.servicio.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Representa un vehiculo de un cliente
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Entity
public class Vehiculo extends EntidadBase implements Serializable {

    private String placa;

    private String marca;

    private String modelo;

    @ManyToOne
    private UsuarioCliente cliente;

    @Enumerated(EnumType.ORDINAL)
    private TipoVehiculo tipoVehiculo;

    public UsuarioCliente getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioCliente cliente) {
        this.cliente = cliente;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
