package ru.tshtk.accounting.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SocksRequest {
    @NotBlank
    private String color;
    @Min(0)
    @Max(100)
    @NotNull
    private Integer cottonPart;
    @Min(1)
    @NotNull
    private Integer quantity;

    public void setColor(String color) {
        this.color = color.trim();
    }

    @Override
    public String toString() {
        return "color: " + color +
                ", cottonPart: " + cottonPart +
                ", quantity: " + quantity;
    }
}
