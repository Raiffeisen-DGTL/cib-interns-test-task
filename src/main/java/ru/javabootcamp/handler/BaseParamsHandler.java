package ru.javabootcamp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseParamsHandler {

    public Map<String, Object> handleParamsException(Exception e, ServletWebRequest request) {

        final Map<String, String> errors = new HashMap<>();
        String[] errorsMsg = e.getMessage().split("\\;");
        for (String msg : errorsMsg)
            errors.put(msg.split(" ", 2)[0], msg.split(" ", 2)[1]);

        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.put("path", request.getRequest().getRequestURI());
        result.put("message", errors);
        return result;
    }
}
