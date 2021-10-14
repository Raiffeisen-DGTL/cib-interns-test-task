package raiffeisen.utils;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * @author voroningg
 */
public class Response {
    public static <T> ResponseEntity<String> ok(T entity) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("content-type", "application/json");
        String json = new Gson().toJson(entity);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> internalServerError() {
        return ResponseEntity.internalServerError().build();
    }

    public static <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> conflict() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
