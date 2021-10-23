package ru.morboui.raiff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.morboui.raiff.entity.ErrorResponce;
import ru.morboui.raiff.exceptions.IncorrectParametersException;
import ru.morboui.raiff.exceptions.InvalidResultException;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectParametersException.class)
    public ErrorResponce onIncorrectParameters(IncorrectParametersException exception) {
        return new ErrorResponce("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidResultException.class)
    public ErrorResponce onInvalidResult(InvalidResultException exception) {
        return new ErrorResponce("Null result", exception.getMessage());
    }
}
