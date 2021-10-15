package ru.raiff.raiffsocksstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException(ComparingOperation operation) {
        super(String.format("Не удалось найти стратегию для оператора %s", operation.toString()));
    }
}
