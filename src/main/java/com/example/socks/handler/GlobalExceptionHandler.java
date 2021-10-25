package com.example.socks.handler;

import com.example.socks.exception.ColorDoesNotExistsException;
import com.example.socks.exception.NotEnoughSocksException;
import com.example.socks.exception.OperationNotFoundException;
import com.example.socks.exception.SocksException;
import com.example.socks.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ColorDoesNotExistsException.class)
    public ResponseEntity<ErrorModel> colorDoesNotExistsExceptionHandler(ColorDoesNotExistsException e) {
        Long timestamp = Instant.now().getEpochSecond();
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ErrorModel(status.value(), "Color does not exists", e.getMessage(), timestamp), status);
    }

    @ExceptionHandler(NotEnoughSocksException.class)
    public ResponseEntity<ErrorModel> notEnoughSocksExceptionHandler(NotEnoughSocksException e) {
        Long timestamp = Instant.now().getEpochSecond();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ErrorModel(status.value(), "Not enough socks", e.getMessage(), timestamp), status);
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity<ErrorModel> operationNotFoundExceptionHandler(OperationNotFoundException e) {
        Long timestamp = Instant.now().getEpochSecond();
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ErrorModel(status.value(), "Operation not found", e.getMessage(), timestamp), status);
    }

    @ExceptionHandler(SocksException.class)
    public ResponseEntity<ErrorModel> socksExceptionHandler(SocksException e) {
        Long timestamp = Instant.now().getEpochSecond();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ErrorModel(status.value(), "Socks exception", e.getMessage(), timestamp), status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> socksExceptionHandler(RuntimeException e) {
        Long timestamp = Instant.now().getEpochSecond();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(
                new ErrorModel(status.value(), "Internal server error", e.getMessage(), timestamp), status);
    }
}
