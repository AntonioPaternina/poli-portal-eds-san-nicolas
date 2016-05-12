package co.com.estacionsannicolas.negocio.beans.registro;

import co.com.estacionsannicolas.servicio.entidades.TipoVehiculo;

/**
 * Bean manejado que representa un vehículo
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class VehiculoBean extends BaseBean {

    private String placa;

    private String marca;

    private String modelo;

    private UsuarioClienteBean cliente;

    private TipoVehiculo tipoVehiculo;

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

    public UsuarioClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioClienteBean cliente) {
        this.cliente = cliente;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

}
