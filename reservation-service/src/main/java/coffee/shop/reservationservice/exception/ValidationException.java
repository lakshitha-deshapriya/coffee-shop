package coffee.shop.reservationservice.exception;

/**
 * Exception to notify validation exceptions
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
