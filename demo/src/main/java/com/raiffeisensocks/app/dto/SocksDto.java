package com.raiffeisensocks.app.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SocksDto {
    @NotEmpty(message = "Mustn't be empty")
    @Pattern(regexp = "^[a-zа-я]+$", message = "Input only latin or russian lowercase letters")
    private String color;

    @NotNull(message = "Mustn't be empty")
    @Range(min = 0, max = 100, message = "Must be between 0 and 100")
    private Integer cottonPart;

    @NotNull(message = "Mustn't be empty")
    @Range(min = 0, max = Integer.MAX_VALUE)
    private Integer quantity;

    public SocksDto(String color, Integer cottonPart, Integer quantity) {
        // for tests
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" + "'color': " + "'" + this.color + "'" + ", " + "'cottonPart': " + this.cottonPart + ", "
                + "'quantity': " + this.quantity + "}";
    }
}
