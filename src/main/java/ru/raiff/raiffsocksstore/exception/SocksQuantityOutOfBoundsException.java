package ru.raiff.raiffsocksstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SocksQuantityOutOfBoundsException extends RuntimeException {

    public SocksQuantityOutOfBoundsException(Integer quantity, Integer requiredQuantity) {
        super(String.format("Количество носков недостаточно для продажи. " +
                "Доступное количество %s, запрашиваемое количество %s", quantity, requiredQuantity));
    }
}
