package com.example.socks.Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

public class Response {
    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    public Response(String message, String debugMessage) {
        this.message = message;
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
