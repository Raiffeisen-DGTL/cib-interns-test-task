package ru.raiffeisen.soksapp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Socks {

    @NotBlank
    private String color;

    @Min(0)
    private Integer cottonPart;

    @Min(1)
    private Integer quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
