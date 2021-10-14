package ru.task.socks.exception;


import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PSQLException.class)
    public ResponseEntity<HttpStatus> badRequestHandler(PSQLException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SocksCustomException.class)
    public ResponseEntity<HttpStatus> socksCustomExceptionHandler(SocksCustomException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HttpStatus> validationExceptionHandler(ConstraintViolationException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
