package ru.raiffeisen.socks.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.raiffeisen.socks.exception.ColorNotFoundException;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.OperationUnknown;
import ru.raiffeisen.socks.exception.SocksNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            ColorNotFoundException.class,
            SocksNotFoundException.class,
            NotEnoughSocksException.class
    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return handle(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationUnknown.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return handle(ex, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handle(RuntimeException ex, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }

}
