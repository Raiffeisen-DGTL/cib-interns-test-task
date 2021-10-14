package ru.task.socks.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SocksDTO {

    private Long id;

    @NotBlank
    private String color;

    @Digits(integer = 3, fraction = 0)
    @Min(value = 0)
    @Max(value = 100)
    private Float cottonPart;

    @Digits(integer = 20, fraction = 0)
    @Min(value = 1L)
    private Float quantity;

    public SocksDTO() {
    }

    public SocksDTO(Long id, String color, Float cottonPart, Float quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Float cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
