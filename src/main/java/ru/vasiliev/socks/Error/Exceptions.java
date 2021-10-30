package ru.vasiliev.socks.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exceptions extends RuntimeException {

    @ExceptionHandler(SocksError.class)
    protected ResponseEntity<SomeExceptions> value(){
            return new ResponseEntity<>(new SomeExceptions("Error in the entered parameters", "Invalid value entered"),
                    HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @Data
    @AllArgsConstructor
    private static class SomeExceptions{
        private String Error;
        private String message;
    }

}
