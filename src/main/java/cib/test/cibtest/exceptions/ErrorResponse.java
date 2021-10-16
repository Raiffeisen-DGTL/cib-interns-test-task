package cib.test.cibtest.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(String code, String message) {
        setCode(code);
        setMessage(message);
    }
}
