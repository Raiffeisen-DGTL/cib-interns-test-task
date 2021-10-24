package ru.tshtk.accounting.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tshtk.accounting.exception.OutcomeImpossibleException;

import javax.validation.ValidationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler({
            ValidationException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class} )
    public ResponseEntity<String> handleValidationException (Exception e) {
        return new ResponseEntity<>("Параметры запроса отсутствуют или имеют некоректный формат.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutcomeImpossibleException.class)
    public ResponseEntity<String> handleOutcomeImpossibleException(OutcomeImpossibleException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}