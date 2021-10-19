package com.example.socksservice.advice;

import com.example.socksservice.exceptions.NotFoundSockException;
import com.example.socksservice.exceptions.NotSufficientStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SocksAdvice {
    @ExceptionHandler({NotSufficientStockException.class, NotFoundSockException.class})
    public ResponseEntity<String> onException(Exception e) {
        return ResponseEntity
                .status(400)
                .body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> onException() {
        return ResponseEntity
                .badRequest()
                .build();
    }
}
