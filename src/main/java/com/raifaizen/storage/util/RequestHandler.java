package com.raifaizen.storage.util;

import org.springframework.http.ResponseEntity;

public class RequestHandler {
    public static ResponseEntity getBadRequest(Exception e) {
        return ResponseEntity.badRequest()
                .header("error", e.getMessage())
                .build();
    }
}
