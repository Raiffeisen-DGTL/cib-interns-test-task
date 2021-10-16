package com.example.accountingofsocks.controller;

import com.example.accountingofsocks.exception.NullQuantityPointerException;
import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.exceptionMapping.SocksIncorrectData;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {
            NullQuantityPointerException.class,
            QuantitySocksOutOfBoundsException.class
    })
    ResponseEntity<SocksIncorrectData> handlerException(RuntimeException exception) {
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            JsonParseException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    ResponseEntity<String> handlerException(Exception exception) {
        return new ResponseEntity<>("Параметры запроса отсутствуют или имеют некорректный формат;", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException(MethodArgumentNotValidException exception) {
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<String> handlerException(ConstraintViolationException exception) {
        var c = exception.getConstraintViolations().iterator().next();
        String str = "Параметр "+ c.getPropertyPath()
                .toString()
                .substring(c.getPropertyPath()
                        .toString()
                        .lastIndexOf(".")+1)+" "+c.getMessage();

        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo("Процента хлопка не может быть больше 100% или меньше 0%");
        return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
    }
}
