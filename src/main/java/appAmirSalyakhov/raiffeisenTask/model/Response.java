package appAmirSalyakhov.raiffeisenTask.model;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class Response {

    private String message;
    private LocalDateTime timestamp;
    private HttpStatus httpDesc;

    public Response(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpDesc = httpStatus;
        this.timestamp = LocalDateTime.now();
    }

    public Response(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Response() {
    }

    public HttpStatus httpDesc() {
        return httpDesc;
    }

    public LocalDateTime getTime() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
