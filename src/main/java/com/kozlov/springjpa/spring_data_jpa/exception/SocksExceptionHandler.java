package com.kozlov.springjpa.spring_data_jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SocksExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ReturnEMessage> handleDataException(IllegalArgumentException e) {
        ReturnEMessage message = new ReturnEMessage();
        message.setMessage(e.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
