package ru.project.raiffeisenbank.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.project.raiffeisenbank.dto.ErrorDTO;
import ru.project.raiffeisenbank.dto.SocksResponse;
import ru.project.raiffeisenbank.exception.SocksNotFoundException;
import ru.project.raiffeisenbank.exception.SocksParamException;

@RestControllerAdvice
public class SocksAdviceConfig extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SocksNotFoundException.class)
    public SocksResponse<?> socksOutcomeExceptionHandler(SocksNotFoundException e) {
        return new SocksResponse<>(new ErrorDTO(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SocksParamException.class)
    public SocksResponse<?> socksParamExceptionHandler(SocksParamException e) {
        return new SocksResponse<>(new ErrorDTO(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Throwable.class)
    public SocksResponse<?> paramExceptionHandler(Throwable e) throws Throwable {
        throw e;
    }
}
