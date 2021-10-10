package ru.danilarassokhin.raiffeisensocks.dto;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SocksIncomeDto {

    @ApiParam(name = "color", example = "yellow", required = true)
    @NotBlank(message = "Socks color can't be null")
    private String color;

    @ApiParam(name = "cottonPart", example = "10", required = true, type = "byte")
    @Min(value = 0, message = "Cotton part must be greater or equal to zero")
    @Max(value = 100, message = "Cotton part must be less or equal to zero")
    private byte cottonPart;

    @ApiParam(name = "quantity", example = "11", required = true, type = "long")
    @Min(value = 1, message = "Socks quantity must be greater than zero")
    private long quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
