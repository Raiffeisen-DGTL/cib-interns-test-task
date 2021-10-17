package com.example.bootcamp.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class SocksDto {

    private String color;
    private int cottonPart;
    private int quantity;

    @ConstructorProperties({"color", "cottonPart", "quantity"})
    public SocksDto(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
