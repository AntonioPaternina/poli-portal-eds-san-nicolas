package co.com.estacionsannicolas.service.exceptions;


public class TooManyPromotionCodesToCreateException extends EdssnServiceException {
    public TooManyPromotionCodesToCreateException() {
        super("La petición excede el máximo de códigos promocionales permitidos");
    }
}
