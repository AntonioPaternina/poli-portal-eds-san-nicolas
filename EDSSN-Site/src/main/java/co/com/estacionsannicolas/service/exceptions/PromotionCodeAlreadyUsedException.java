package co.com.estacionsannicolas.service.exceptions;

public class PromotionCodeAlreadyUsedException extends EdssnServiceException {
    public PromotionCodeAlreadyUsedException() {
        super("Este código promocional ya ha sido usado");
    }

}
