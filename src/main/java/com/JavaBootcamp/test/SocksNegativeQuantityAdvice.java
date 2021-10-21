package com.JavaBootcamp.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SocksNegativeQuantityAdvice extends Throwable {

    @ResponseBody
    @ExceptionHandler(SocksNegativeQuantityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String socksNotFoundHandler(SocksNegativeQuantityException ex) {
        return ex.getMessage();
    }
}
