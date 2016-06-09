package co.com.estacionsannicolas.service.exceptions;


public class NotEnoughPointsToRedeemAwardException extends EdssnServiceException {

    public NotEnoughPointsToRedeemAwardException() {
        super("El usuario no tiene suficientes puntos para redimir el premio");
    }

}
