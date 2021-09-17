package com.example.sock.controller;

import com.example.sock.domain.ErrorResponce;
import com.example.sock.exceptions.IncorrectParametersException;
import com.example.sock.exceptions.NullResultException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectParametersException.class)
    public ErrorResponce onIncorrectParameters(IncorrectParametersException exception){
        return new ErrorResponce("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullResultException.class)
    public ErrorResponce onNullResult(NullResultException exception){
        return new ErrorResponce("Null result", exception.getMessage());
    }

}
