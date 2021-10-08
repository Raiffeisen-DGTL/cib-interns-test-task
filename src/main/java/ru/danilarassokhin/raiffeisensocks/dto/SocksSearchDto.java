package ru.danilarassokhin.raiffeisensocks.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NotNull(message = "Search data can't be null")
public class SocksSearchDto {

    @NotBlank(message = "Socks color can't be empty")
    private String color;
    @NotBlank(message = "Operation condition required")
    private String operation;
    @Min(value = 0, message = "Cotton part must be greater or equals to zero")
    @Max(value = 100, message = "Cotton part can't be greater than 100")
    private byte cottonPart;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }
}
