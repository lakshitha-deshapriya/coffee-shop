package coffee.shop.auth.handler;

import coffee.shop.auth.dto.Error;
import coffee.shop.auth.exception.NotFoundException;
import coffee.shop.auth.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.PostConstruct;

/**
 * Exception handler for exceptions thrown at controller level
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

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Error> handleValidationException(ValidationException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
