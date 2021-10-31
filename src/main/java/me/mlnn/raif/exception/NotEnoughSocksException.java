package me.mlnn.raif.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested value is more than available")
public class NotEnoughSocksException extends RuntimeException {
    public NotEnoughSocksException() {
        super("Requested value is more than available");
    }
}
