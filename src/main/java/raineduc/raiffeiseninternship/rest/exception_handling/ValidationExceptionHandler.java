package raineduc.raiffeiseninternship.rest.exception_handling;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import raineduc.raiffeiseninternship.services.exceptions.NotEnoughSocksException;


@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleValidationException(BindException ex, WebRequest request) {
        FieldError firstError = ex.getFieldError();
        String message = firstError.getField() + ": " + firstError.getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NotEnoughSocksException.class)
    protected ResponseEntity<String> handleNotEnoughSocksException(NotEnoughSocksException ex, WebRequest request) {
        String message = "There are not so many socks in warehouse to outcome";
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class, NumberFormatException.class})
    protected ResponseEntity<String> handleInputException(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Input is invalid");
    }
}
