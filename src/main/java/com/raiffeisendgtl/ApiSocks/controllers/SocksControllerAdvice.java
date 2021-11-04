package com.raiffeisendgtl.ApiSocks.controllers;

import com.raiffeisendgtl.ApiSocks.components.SocksException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class SocksControllerAdvice {

    @ExceptionHandler(SocksException.class)
    public ResponseEntity<String> handleError(SocksException e) {
        return new ResponseEntity<>(e.getError().getStatusError());
    }

}
