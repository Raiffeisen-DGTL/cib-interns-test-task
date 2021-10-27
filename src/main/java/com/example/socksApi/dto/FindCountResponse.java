package com.example.socksApi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindCountResponse extends SockResponse{

    private Integer result;

    public FindCountResponse(Boolean success, LocalDateTime dateTime, String message, Integer result) {
        super.setSuccess(success);
        super.setTimestamp(dateTime);
        super.setMessage(message);
        this.result = result;
    }
}
