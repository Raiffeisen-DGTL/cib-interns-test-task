package ru.vsu.rest.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vsu.rest.model.ErrorModel;
import ru.vsu.rest.model.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class SocksStorageExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorModel> handleCvl(ConstraintViolationException e) {
    var errors = e.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .map(ValidationError::new)
      .collect(Collectors.toUnmodifiableList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel(errors));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorModel> onMethodArgumentNotValidException(
    MethodArgumentNotValidException e) {
    var errors = e.getBindingResult()
      .getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .map(ValidationError::new)
      .collect(Collectors.toUnmodifiableList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel(errors));
  }
}
