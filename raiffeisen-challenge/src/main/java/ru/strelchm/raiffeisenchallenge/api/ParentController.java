package ru.strelchm.raiffeisenchallenge.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.strelchm.raiffeisenchallenge.exception.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class ParentController {
    static final String USER_CONTEXT = "USER_CONTEXT";
    static final String NULL_ID_REQUEST_EXCEPTION = "Id cannot be null";

    @ExceptionHandler({FileNotFoundException.class, NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNotFoundExceptions(Exception ex) {
        return getResponseFromException(ex);
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public HashMap<String, String> handleAccessDeniedExceptions(Exception ex) {
        return getResponseFromException(ex);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleBadRequestExceptions(Exception ex) {
        return getResponseFromException(ex);
    }

    @ExceptionHandler(AlreadyExistedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleAlreadyExistedExceptions(Exception ex) {
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
    public HashMap<String, String> handleBadRequestExceptions(MethodArgumentNotValidException ex) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.put("error", ex.getClass().getSimpleName());
        ex.printStackTrace();
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public HashMap<String, String> handleIntervalServerExceptions(Exception ex) {
        return getResponseFromException(ex);
    }
}
