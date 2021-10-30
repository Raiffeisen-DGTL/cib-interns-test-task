package com.lazarev.socksapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@ControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotEnoughSocksOnStorehouseException.class)
    public ResponseEntity<?> notEnoughSocksException(NotEnoughSocksOnStorehouseException ex){
        return badRequestResponseBody(ex);
    }

    @ExceptionHandler(SockNotFoundException.class)
    public ResponseEntity<?> sockNotFoundException(SockNotFoundException ex){
        return badRequestResponseBody(ex);
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity<?> operationNotFoundException(OperationNotFoundException ex){
        return badRequestResponseBody(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerError(Exception ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        log.error(message, ex);
        Map<String, String> errors = Map.of("HttpStatus", status.name(), "message", message);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);

        Map<String, List<String>> errors = new LinkedHashMap<>();

        List<String> errorMessages =
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        errors.put("errors", errorMessages);

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }


    private ResponseEntity<?> badRequestResponseBody(RuntimeException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();
        log.error(message, ex);
        Map<String, String> errors = Map.of("HttpStatus", status.name(), "message", message);

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }
}
