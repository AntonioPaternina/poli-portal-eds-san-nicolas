package co.com.estacionsannicolas.service.exceptions;

public class UsernameIsNotUniqueException extends EdssnServiceException {
    public UsernameIsNotUniqueException() {
        super("El nombre de usuario ya está asignado a otro usuario");
    }
}
