package coffee.shop.shopservice.handler;

import coffee.shop.shopservice.dto.Error;
import coffee.shop.shopservice.exception.NotFoundException;
import coffee.shop.shopservice.exception.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.PostConstruct;

/**
 * Handler of controller exceptions
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
}
