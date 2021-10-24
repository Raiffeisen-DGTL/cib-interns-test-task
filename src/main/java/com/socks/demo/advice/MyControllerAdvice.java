package com.socks.demo.advice;

import com.socks.demo.exception.IncorrectCottonPartException;
import com.socks.demo.exception.IncorrectParametersException;
import com.socks.demo.exception.IncorrectQuantityException;
import com.socks.demo.exception.NotEnoughSocksException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(NotEnoughSocksException.class)
    public ResponseEntity<String> notEnoughSocksException(NotEnoughSocksException notEnoughSocksException) {
        return new ResponseEntity<>(notEnoughSocksException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectParametersException.class)
    public ResponseEntity<String> incorrectParametersException(IncorrectParametersException incorrectParametersException) {
        return new ResponseEntity<>(incorrectParametersException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectCottonPartException.class)
    public ResponseEntity<String> incorrectCottonPartException(IncorrectCottonPartException incorrectCottonPartException) {
        return new ResponseEntity<>(incorrectCottonPartException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectQuantityException.class)
    public ResponseEntity<String> incorrectQuantityException(IncorrectQuantityException incorrectQuantityException) {
        return new ResponseEntity<>(incorrectQuantityException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

}
