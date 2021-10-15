package ru.raiff.raiffsocksstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CottonPartMoreThan100Exception extends RuntimeException{
    public CottonPartMoreThan100Exception() {
        super("Количество хлопка не может превышать 100%");
    }
}
