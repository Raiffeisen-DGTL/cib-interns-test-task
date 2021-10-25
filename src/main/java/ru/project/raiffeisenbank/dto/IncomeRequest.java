package ru.project.raiffeisenbank.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class IncomeRequest {
    @NotNull
    private String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private int cottonPart;

    @NotNull
    @Min(value = 0)
    private Long quantity;

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
}
