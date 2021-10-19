package ru.raiffeisen.cibinternstesttask.system;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.raiffeisen.cibinternstesttask.system.dto.ErrorResponse;

/**
 * Обработка исключений от сервисного слоя приложения.
 */
@ControllerAdvice
@Slf4j
public class ResponseStatusExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Перехватывает исключения от сервисного слоя,
     * формирует DTO с информацией об ошибке и пишет лог с исключением.
     *
     * @param ex перехваченное ислючение
     * @param request информация о запросе
     * @return сообщение об ошибке
     */
    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<ErrorResponse> logResponseStatus(
            ResponseStatusException ex,
            ServletWebRequest request) {
        var errorResponse = new ErrorResponse(
                Instant.now().getEpochSecond(),
                ex.getStatus().value(),
                ex.getStatus().name(),
                ex.getReason(),
                request.getRequest().getRequestURI());
        log.error(ex.toString());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
