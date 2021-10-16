package cib.test.cibtest.exceptions.controller;

import cib.test.cibtest.exceptions.ErrorResponse;
import cib.test.cibtest.exceptions.excclass.DatabaseException;
import cib.test.cibtest.exceptions.excclass.UrlException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SockExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UrlException.class)
    public ErrorResponse onIncorrectParameters(UrlException exception){
        return new ErrorResponse("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public ErrorResponse onNullResult(DatabaseException exception){
        return new ErrorResponse("Null result", exception.getMessage());
    }
}