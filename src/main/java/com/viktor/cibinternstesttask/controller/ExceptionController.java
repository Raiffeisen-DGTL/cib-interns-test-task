package com.viktor.cibinternstesttask.controller;

import com.viktor.cibinternstesttask.exception.WrongParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(WrongParameterException.class)
    public ResponseEntity<String> wrongParameter(WrongParameterException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
