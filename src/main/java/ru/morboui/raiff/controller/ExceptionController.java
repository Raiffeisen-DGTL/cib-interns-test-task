package ru.morboui.raiff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.morboui.raiff.entity.ErrorResponse;
import ru.morboui.raiff.exceptions.IncorrectParametersException;
import ru.morboui.raiff.exceptions.InvalidResultException;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectParametersException.class)
    public ErrorResponse onIncorrectParameters(IncorrectParametersException exception) {
        return new ErrorResponse("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidResultException.class)
    public ErrorResponse onInvalidResult(InvalidResultException exception) {
        return new ErrorResponse("Null result", exception.getMessage());
    }
}
