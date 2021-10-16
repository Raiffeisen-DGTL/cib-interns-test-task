package com.example.bootcamp.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class SocksDto {

    private String color;
    private short cottonPart;
    private int quantity;

    @ConstructorProperties({"color", "cottonPart", "quantity"})
    public SocksDto(String color, short cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
