package com.java.task.handlers;

import com.java.task.exception.OutcomeFromNotExistingSocksException;
import com.raiffeisen.task.exception.OutcomeMoreThenRemainderSocksException;
import com.raiffeisen.task.exception.UnsupportableOperationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class SocksControllerHandler {

    @SneakyThrows
    @ExceptionHandler(UnsupportableOperationException.class)
    public void handleUnsupportableOperationException(
            UnsupportableOperationException unsupportableOperationException,
            HttpServletResponse httpServletResponse
    ) {
        log.error(unsupportableOperationException.getMessage() + unsupportableOperationException.getReceivedOperation());
        httpServletResponse.sendError(400);
        httpServletResponse.setStatus(400);
    }

    @SneakyThrows
    @ExceptionHandler(OutcomeMoreThenRemainderSocksException.class)
    public void handleOutcomeMoreThenRemainderException(
            OutcomeMoreThenRemainderSocksException thenRemainderSocksException,
            HttpServletResponse httpServletResponse
    ) {
        log.error(thenRemainderSocksException.getMessage());
        httpServletResponse.sendError(400);
        httpServletResponse.setStatus(400);
    }

    @SneakyThrows
    @ExceptionHandler(OutcomeFromNotExistingSocksException.class)
    public void handleOutcomeFromNotExistingSocksException(
            OutcomeFromNotExistingSocksException existingSocksException,
            HttpServletResponse httpServletResponse
    ) {
        log.error(existingSocksException.getMessage());
        httpServletResponse.sendError(400);
        httpServletResponse.setStatus(400);
    }

    @SneakyThrows
    @ExceptionHandler(BindException.class)
    public void handleBindException(
            BindException bindException,
            HttpServletResponse httpServletResponse
    ) {
        log.error(bindException.getMessage());
        httpServletResponse.sendError(400);
        httpServletResponse.setStatus(400);
    }

    @SneakyThrows
    @ExceptionHandler(Throwable.class)
    public void handleInternalHttpException(Throwable throwable, HttpServletResponse httpServletResponse) {
        log.error(throwable.getMessage());
        httpServletResponse.sendError(500);
        httpServletResponse.setStatus(500);
    }
}
