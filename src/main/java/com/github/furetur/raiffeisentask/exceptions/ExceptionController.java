package com.github.furetur.raiffeisentask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
  @ExceptionHandler(NotEnoughSocksException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody String handleException(NotEnoughSocksException e) {
    return e.getMessage();
  }
}
