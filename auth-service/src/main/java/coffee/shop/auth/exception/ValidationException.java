package coffee.shop.auth.exception;

/**
 * Exception to notify validation errors
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
