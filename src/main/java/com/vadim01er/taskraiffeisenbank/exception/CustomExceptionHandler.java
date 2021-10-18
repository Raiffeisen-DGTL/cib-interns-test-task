package com.vadim01er.taskraiffeisenbank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class, IllegalArgumentException.class })
    public ResponseEntity<Object> handleBadRequestExceptions() {
        return ResponseEntity.badRequest().build();
    }


    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleInternalServerErrorExceptions() {
        return ResponseEntity.internalServerError().build();
    }

}
