package net.javaguides.springboot.springsecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionAdvice {
    private static final String INTERNAL_SERVER_MESSAGE = "Invalid response from the server";

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDetails> handleForbidden(Throwable exception) {
        String message = exception.getMessage();
        log.error(message);
        return buildResponse(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDetails> handleInternalException(Throwable exception) {
        String message = exception.getMessage();
        if (message.isEmpty()) {
            message = INTERNAL_SERVER_MESSAGE;
        }
        log.error(message);
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDetails> buildResponse(String message, HttpStatus httpStatus) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .error(message)
                .build();
        return ResponseEntity.status(httpStatus).body(errorDetails);
    }
}
