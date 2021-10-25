package com.example.socks.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "error")
    private String error;

    @JsonProperty(value = "message")
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "timestamp")
    private Long timestamp;

}
