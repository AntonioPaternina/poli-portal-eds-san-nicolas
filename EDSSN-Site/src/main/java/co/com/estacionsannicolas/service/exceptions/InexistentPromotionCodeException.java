package co.com.estacionsannicolas.service.exceptions;

public class InexistentPromotionCodeException extends EdssnServiceException {
    public InexistentPromotionCodeException() {
        super("El código promocional no existe");
    }

}
