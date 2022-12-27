package coffee.shop.shopservice.exception;

/**
 * Exception to notify that resource not found
 */
public class NotFoundException extends Exception{

    public NotFoundException(String message)
    {
        super( message );
    }
}