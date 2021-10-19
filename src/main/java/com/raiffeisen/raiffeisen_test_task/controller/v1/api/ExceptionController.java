package com.raiffeisen.raiffeisen_test_task.controller.v1.api;

import com.raiffeisen.raiffeisen_test_task.exception.RaiffeisenException;
import com.raiffeisen.raiffeisen_test_task.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RaiffeisenException.ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception ex) {
        return new ErrorResponse(new Date(), ex.getMessage());
    }

    @ExceptionHandler(RaiffeisenException.NullResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullResultException(Exception ex) {
        return new ErrorResponse(new Date(), ex.getMessage());
    }


    @ExceptionHandler(RaiffeisenException.IncorrectParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(Exception ex) {
        return new ErrorResponse(new Date(), ex.getMessage());
    }
}
