package ru.tshtk.accounting.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SocksRequest {

    @NotBlank
    private String color;
    @Min(0)
    @Max(100)
    private int cottonPart;
    @Min(1)
    private int quantity;

    @Override
    public String toString() {
        return "color: " + color +
                ", cottonPart: " + cottonPart +
                ", quantity: " + quantity;
    }
}
