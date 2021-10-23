package com.raiffeisen.socks.controller;

import com.raiffeisen.socks.dto.ErrorMessage;
import com.raiffeisen.socks.exceptions.IncorrectSockFormatException;
import com.raiffeisen.socks.exceptions.NotFoundSockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorMessage notFoundSockExceptionHandler(NotFoundSockException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorMessage IncorrectFormatExceptionHandler(IncorrectSockFormatException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler()
    public ErrorMessage InternalServerExceptionHandler(Exception exception) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
