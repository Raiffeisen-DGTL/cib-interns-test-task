package com.raifaisentask.data;

import io.swagger.annotations.ApiModelProperty;

public class Socks {

    private @ApiModelProperty(hidden = true) Long id;
    private String color;
    private int cottonPart;
    private Long quantity;

    public Socks() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
