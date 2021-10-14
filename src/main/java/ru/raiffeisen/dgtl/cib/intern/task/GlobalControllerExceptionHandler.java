package ru.raiffeisen.dgtl.cib.intern.task;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotEnoughSocksQuantityException;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotFoundOperation;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotFoundSocks;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundOperation.class)
    public ResponseEntity<String> handleConversionFailed(NotFoundOperation ex) {
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundSocks.class)
    public ResponseEntity<String> handleConversionFailed(NotFoundSocks ex) {
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughSocksQuantityException.class)
    public ResponseEntity<String> handleConversionFailed(NotEnoughSocksQuantityException ex) {
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}