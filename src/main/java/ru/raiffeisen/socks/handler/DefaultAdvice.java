package ru.raiffeisen.socks.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.raiffeisen.socks.dto.ErrorResponse;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.UnsupportedSocksOperationException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({
            UnsupportedSocksOperationException.class,
            NotEnoughSocksException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
