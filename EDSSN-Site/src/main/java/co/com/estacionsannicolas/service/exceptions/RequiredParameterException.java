package co.com.estacionsannicolas.service.exceptions;


public class RequiredParameterException extends EdssnServiceException {

    public RequiredParameterException() {
        super("Parámetro requerido no ingresado");
    }
}
