package com.example.socks2.dto;

public class SocksDto {

    private final String color;
    private final Long cottonPart;
    private final Long quantity;

    public SocksDto(String color, Long cottonPart, Long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public Long getCottonPart() {
        return cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }
}
