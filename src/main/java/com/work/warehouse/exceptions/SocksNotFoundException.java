package com.work.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SocksNotFoundException extends RuntimeException {
    public SocksNotFoundException(String msg) {
        super (msg);
    }
}
