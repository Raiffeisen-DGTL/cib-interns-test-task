package com.task.raif.controller;

import com.task.raif.exception.InvalidQuantityException;
import com.task.raif.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {
    private <T> ResponseEntity<?> getErrorMessage(T body) {
        return new ResponseEntity<>(Map.of("error", body), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        Map<String, String> body = errors.stream()
                .filter(error -> error.getDefaultMessage() != null)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return getErrorMessage(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        String body = "Параметры запроса отсутствуют или имеют некорректный формат: " + ex.getName();
        return getErrorMessage(body);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        String body = "Пропущено поле " + ex.getParameterName();
        return getErrorMessage(body);
    }

    @ExceptionHandler({InvalidQuantityException.class, NotFoundException.class})
    public ResponseEntity<?> NotFoundExceptionHandler(RuntimeException ex) {
        String body =  ex.getMessage();
        return getErrorMessage(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationExceptionHandler(RuntimeException ex) {
        String body = ex.getMessage();
        return getErrorMessage(body);
    }
}
