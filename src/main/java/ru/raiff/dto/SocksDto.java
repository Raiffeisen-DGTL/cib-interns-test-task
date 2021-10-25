package ru.raiff.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Setter
@Getter
public class SocksDto {
    @NotBlank
    private String color;
    @Min(value = 1)
    @Max(value = 100)
    private int cottonPart;
    @Min(2)
    private int quantity;

    @Override
    public String toString() {
        return "Socks - color: " + color +
                ", cottonPart: " + cottonPart +
                ", quantity: " + quantity;
    }
}
