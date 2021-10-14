package ru.raif.socks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.raif.socks.exception.InsufficientQuantityException;
import ru.raif.socks.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        return errors.stream()
                .filter(error -> error.getDefaultMessage() != null)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        return "параметры запроса отсутствуют или имеют некорректный формат: " + ex.getName();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({InsufficientQuantityException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String NotFoundExceptionHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ConstraintViolationExceptionHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String SQLExceptionHandler(RuntimeException ex) {
        logger.error(ex.getMessage());
        return "Произошла критическая ошибка при работе с базой данных";
    }
}
