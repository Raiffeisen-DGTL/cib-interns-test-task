package me.mlnn.raif.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested socks not found")
public class SocksNotFoundException extends RuntimeException {
    public SocksNotFoundException() {
        super("Requested socks not found");
    }
}

