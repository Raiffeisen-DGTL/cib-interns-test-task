package org.vetirdoit.sock.registration.controllers;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler({ConstraintViolationException.class, ConversionFailedException.class})
    public ResponseEntity<String> onWrongRequestData() {
        String message = "There is no request parameters or they are in the wrong format!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
