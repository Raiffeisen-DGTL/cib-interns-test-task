package com.mn000009.warehouse.controller;

import com.mn000009.warehouse.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler
  ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception) {
    ConstraintViolation<?> exceptionPart = exception.getConstraintViolations().iterator().next();
    String message = exceptionPart.getMessage();
    String field = exceptionPart.getPropertyPath().toString();
    return new ResponseEntity<>("Received data is incorrect: field " +
        (field.equals("title") ? "color" : field) + ", " + message, BAD_REQUEST);
  }

  @ExceptionHandler(value = {
      IllegalColorException.class,
      IllegalQuantityException.class,
      IllegalCottonPartException.class,
      IncorrectOperationException.class,
      NotEnoughSuchSocksException.class
  })
  ResponseEntity<String> handlerIncorrectOperationException(RuntimeException exception) {
    return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
  }

}
