package com.raiffeisen.task.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class OutcomeMoreThenRemainderSocksException extends RuntimeException {

    public OutcomeMoreThenRemainderSocksException(String message) {
        super(message);
    }
}
