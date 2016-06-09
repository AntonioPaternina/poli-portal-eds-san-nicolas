package co.com.estacionsannicolas.service.exceptions;

public class EdssnServiceException extends Exception {

    public EdssnServiceException() {
        super("Error General");
    }

    public EdssnServiceException(String message) {
        super(message);
    }
}
