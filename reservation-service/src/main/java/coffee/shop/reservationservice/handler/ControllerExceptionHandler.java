package coffee.shop.reservationservice.handler;

import coffee.shop.reservationservice.dto.Error;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.exception.NotImplementedException;
import coffee.shop.reservationservice.exception.ReservationFailedException;
import coffee.shop.reservationservice.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.PostConstruct;

/**
 * Exception handler for controller exceptions
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @Value("${custom.log.name}")
    private String logName;

    private Logger logger;

    @PostConstruct
    public void setupLogger() {
        logger = LoggerFactory.getLogger(logName + " : ExceptionHandler : ");
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Error> handleNotFoundException(NotFoundException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotImplementedException.class})
    public ResponseEntity<Error> handleNotImplementedException(NotImplementedException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.NOT_IMPLEMENTED.value(), ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Error> handleValidationException(ValidationException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ReservationFailedException.class})
    public ResponseEntity<Error> handleReservationFailedException(ReservationFailedException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
