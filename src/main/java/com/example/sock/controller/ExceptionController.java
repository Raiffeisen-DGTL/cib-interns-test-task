package com.example.sock.controller;

import com.example.sock.domain.ErrorResponse;
import com.example.sock.exceptions.IncorrectParametersException;
import com.example.sock.exceptions.NullResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectParametersException.class)
    public ErrorResponse onIncorrectParameters(IncorrectParametersException exception){
        return new ErrorResponse("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Для возврата клиенту вместо null - json c сообщением, что ничего не найдено
    @ExceptionHandler(NullResultException.class)
    public ErrorResponse onNullResult(NullResultException exception){
        return new ErrorResponse("Null result", exception.getMessage());
    }

}
