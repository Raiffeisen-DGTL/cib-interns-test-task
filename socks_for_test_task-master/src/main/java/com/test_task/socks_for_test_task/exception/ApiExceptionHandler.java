package com.test_task.socks_for_test_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiInvalidParameterException.class)
    public ResponseEntity<ExceptionMessage> handleException(ApiInvalidParameterException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}
