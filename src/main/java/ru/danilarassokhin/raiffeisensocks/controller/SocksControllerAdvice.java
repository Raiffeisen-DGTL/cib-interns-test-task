package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.service.dto.ServiceResponseStatus;

@RestControllerAdvice
public class SocksControllerAdvice {

  @ExceptionHandler(DataValidityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<String> handleDataValidityException(DataValidityException exception) {
    return new ResponseDto<>(ServiceResponseStatus.INVALID_DATA.name(), exception.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<String> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException exception) {
    return new ResponseDto<>(ServiceResponseStatus.INVALID_DATA.name(),
        "Check your request data. May be it's null or empty? Well, it shouldn't be");
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto<String> handleRequestParameterException(
      MissingServletRequestParameterException exception) {
    return new ResponseDto<>(ServiceResponseStatus.INVALID_DATA.name(), exception.getMessage());
  }

}
