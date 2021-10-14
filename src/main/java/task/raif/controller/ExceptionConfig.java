package task.raif.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.raif.exception.NotEnoughSocksException;
import task.raif.exception.SocksValidationException;


@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler({SocksValidationException.class,
                       NotEnoughSocksException.class})
    public ResponseEntity<String> handleConverterErrors(Exception exception) {
        return ResponseEntity.status(400)
                             .body(exception.getMessage());
    }

}

