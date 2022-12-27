package coffee.shop.reservationservice.exception;

/**
 * Exception to notify that reservation has failed
 */
public class ReservationFailedException extends Exception {
    public ReservationFailedException(String message) {
        super(message);
    }
}