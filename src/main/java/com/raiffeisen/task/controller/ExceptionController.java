package com.raiffeisen.task.controller;

import com.raiffeisen.task.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleException(ValidationException e){
        return ResponseEntity.badRequest().body("{\n" +
                "\"message\" : \"" +
                e.getMessage() + "\"\n}");
    }
}
