package com.example.socks.management.dto;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;


public class SocksDto {
    @NotNull
    private Color color;
    @Max(100)
    @Min(0)
    private Integer cottonPart;
    @Min(1)
    private Integer quantity;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

