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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                '}';
    }
}
