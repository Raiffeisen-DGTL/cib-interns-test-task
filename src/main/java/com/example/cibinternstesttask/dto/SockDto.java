package com.example.cibinternstesttask.dto;

import com.example.cibinternstesttask.model.Sock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SockDto {
    private String color;

    private Long cottonPart;

    private Long quantity;

    public Sock toSock() {
        return new Sock(color, cottonPart, quantity);
    }
}
