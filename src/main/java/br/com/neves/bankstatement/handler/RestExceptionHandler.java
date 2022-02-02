package br.com.neves.bankstatement.handler;

import br.com.neves.bankstatement.exception.AccountNotFoundException;
import br.com.neves.bankstatement.model.ExceptionDetails;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
// -------------------------- OTHER METHODS --------------------------

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(prepareDetails(ex, "Runtime Exception", HttpStatus.INTERNAL_SERVER_ERROR.value(), request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionDetails prepareDetails(Exception ex, String title, int status, HttpServletRequest request) {
        return prepareDetails(ex, title, status, ex.getMessage(), request);
    }

    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<Object> handleClientNotFoundException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(prepareDetails(ex, "Not found exception", HttpStatus.BAD_REQUEST.value(), request), HttpStatus.BAD_REQUEST);
    }

    private ExceptionDetails prepareDetails(Exception ex, String title, int status, String message, HttpServletRequest request) {
        return ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .title(title)
                .details(message)
                .developerMessage(ex.getClass().getSimpleName())
                .path(request.getRequestURL().toString())
                .build();
    }
}
