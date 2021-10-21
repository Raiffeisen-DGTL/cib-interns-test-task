package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class SocksControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Parameters are incorrect")
    public void handleConstraintViolationException(ConstraintViolationException ex) {
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Socks are not found")
    public void handleNoSuchElementException(NoSuchElementException ex) {
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception ex) {
    }

}
