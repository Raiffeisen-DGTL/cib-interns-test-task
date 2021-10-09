package ru.strelchm.raiffeisenchallenge.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.strelchm.raiffeisenchallenge.exception.BadRequestException;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class ParentController {
    static final String NULL_ID_REQUEST_EXCEPTION = "Id cannot be null";

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleBadRequestExceptions(Exception ex) {
        return getResponseFromException(ex);
    }

    @NotNull
    private HashMap<String, String> getResponseFromException(Exception ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("error", ex.getClass().getSimpleName());
        ex.printStackTrace();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequestExceptions(MethodArgumentNotValidException ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.put("error", ex.getClass().getSimpleName());
        ex.printStackTrace();
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleIntervalServerExceptions(Exception ex) {
        return getResponseFromException(ex);
    }
}
