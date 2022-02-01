package com.example.socks.Util;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Response {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    public Response(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
