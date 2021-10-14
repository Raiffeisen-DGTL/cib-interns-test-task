package com.example.company.sock.ExceptionHandle;

import com.example.company.exception.InsufficientDataException;
import com.example.company.exception.NullResultException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NonUniqueResultException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientDataException.class)
    public Message insufficientData(InsufficientDataException exception){
        return new Message("Insufficient data", exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Message insufficientData(DataIntegrityViolationException exception){
        return new Message("Insufficient data", exception.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullResultException.class)
    public Message nullResult(NullResultException exception){
        return new Message("Null data", exception.getMessage());
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public Message multiple(NonUniqueResultException exception){
        return new Message("multiple data", exception.getMessage());
    }
}
