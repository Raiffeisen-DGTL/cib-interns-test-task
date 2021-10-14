package com.github.el_hopaness_romtic.test_task.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class SocksBatch {

    @Id
    private Integer id;

    @NotBlank(message = "Color must contain at least 1 non-whitespace character")
    public String color;

    @Range(min = 0, max = 100, message = "CottonPart must be in range from 0 to 100")
    public Integer cottonPart;

    @Positive(message = "Quantity must be a positive value")
    public Integer quantity;

    public SocksBatch(String color, Integer cottonPart, Integer quantity) {
        if (color == null || cottonPart == null || quantity == null)
            throw new IllegalArgumentException("Null arguments are not allowed");

        this.color = color.trim();
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
