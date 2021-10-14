package ru.task.socks.model.dto;

import javax.validation.constraints.*;

public class WarehouseDTO {

    @NotBlank
    private String color;

    @NotBlank
    private String operation;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    @Min(value = 0)
    @Max(value = 100)
    private Float cottonPart;

    public WarehouseDTO() {
    }

    public WarehouseDTO(String color, String operation, Float cottonPart) {
        this.color = color;
        this.operation = operation;
        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public String getOperation() {
        return operation;
    }

    public Float getCottonPart() {
        return cottonPart;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setCottonPart(Float cottonPart) {
        this.cottonPart = cottonPart;
    }
}
