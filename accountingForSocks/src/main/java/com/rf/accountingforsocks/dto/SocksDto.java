package com.rf.accountingforsocks.dto;

import javax.validation.constraints.*;
import java.util.UUID;

public class SocksDto {
    private UUID id;

    @NotBlank()
    private String color;

    @Min(value = 0)
    @Max(value = 100)
    @NotNull
    private Integer cottonPart;

    @NotNull
    @Positive
    private Integer quantity;

    public SocksDto() {
    }

    public SocksDto(UUID id, String color, Integer cottonPart, Integer quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
