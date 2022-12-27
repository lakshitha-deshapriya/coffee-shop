package coffee.shop.queueservice.exception;

/**
 * Exception to notify that Queue cannot be found
 */
public class QueueNotFoundException extends Exception{
    public QueueNotFoundException(String message)
    {
        super( message );
    }
}
