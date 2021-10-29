package com.example.socksspring;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Socks {
    private final Long id;
    private final String color;

    @Min(0)
    @Max(100)
    private final Integer cottonPart;

    @Min(0)
    private final Integer quantity;

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }

    @JsonCreator
    public Socks(
            @JsonProperty("id") Long id,
            @JsonProperty("color") String color,
            @JsonProperty("cottonPart")  Integer cottonPart,
            @JsonProperty("quantity") Integer quantity) {

        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
