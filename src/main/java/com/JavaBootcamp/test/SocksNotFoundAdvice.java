package com.JavaBootcamp.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocksNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SocksNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String socksNotFoundHandler(SocksNotFoundException ex) {
        return ex.getMessage();
    }
}
