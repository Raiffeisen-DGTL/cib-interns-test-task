package com.raiffeisen.bootcamps.astakhovartem.socksapi.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SocksGlobalExceptionHandler {

    @ExceptionHandler(SocksIncorrectDataException.class)
    public ErrorResponse onSocksIncorrectData(SocksIncorrectDataException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}