package coffee.shop.queueservice.handler;

import coffee.shop.queueservice.dto.Error;
import coffee.shop.queueservice.exception.NotFoundException;
import coffee.shop.queueservice.exception.QueueNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.PostConstruct;

/**
 * Handler for exceptions thrown at controller level
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

    @ExceptionHandler(value = {QueueNotFoundException.class})
    public ResponseEntity<Error> handleQueueNotFoundException(QueueNotFoundException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new Error(HttpStatus.NO_CONTENT.value(), ex.getMessage()), HttpStatus.NO_CONTENT);
    }
}
