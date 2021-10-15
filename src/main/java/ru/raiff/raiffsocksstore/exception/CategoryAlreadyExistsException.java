package ru.raiff.raiffsocksstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException() {
        super("Категория с указанным цветом и количеством хлопка уже существует");
    }
}
