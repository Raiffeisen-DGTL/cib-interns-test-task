package com.fedoseeva.socksaccountingapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IncomeAndOutcomeDTORequest {

    @NotEmpty
    private String color;

    @NotNull
    @Min(0)
    @Max(100)
    private int cottonPart;

    @NotNull
    @Min(0)
    private int quantity;

    public IncomeAndOutcomeDTORequest(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }
}
