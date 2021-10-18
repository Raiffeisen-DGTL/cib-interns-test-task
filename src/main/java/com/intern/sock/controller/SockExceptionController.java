package com.intern.sock.controller;

import com.intern.sock.exceptions.DatabaseException;
import com.intern.sock.exceptions.ErrorResponse;
import com.intern.sock.exceptions.UrlException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SockExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UrlException.class)
    public ErrorResponse incorrectParameters(UrlException exception){
        return new ErrorResponse("Incorrect parameter",exception.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public ErrorResponse nullResult(DatabaseException exception){
        return new ErrorResponse("Null result", exception.getMessage());
    }

}
