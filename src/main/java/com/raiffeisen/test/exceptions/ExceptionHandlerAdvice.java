package com.raiffeisen.test.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSuchOperationException.class})
    public ResponseEntity<ApiError> handleNotSuchOperationException(NoSuchOperationException ex) {
        log.error(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InsufficientQuantityException.class})
    public ResponseEntity<ApiError> handleInsufficientQuantityException(InsufficientQuantityException ex) {
        log.error(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidParamsException.class})
    public ResponseEntity<ApiError> handleInvalidParamsException(InvalidParamsException ex) {
        log.error(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
