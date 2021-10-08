package com.example.testtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException{

  public InternalServerError(String message){
    super(message);
  }
}
