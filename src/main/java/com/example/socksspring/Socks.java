package com.example.socksspring;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Socks {
    private final Long id;
    private final String color;
    private final int cottonPart;
    private final int quantity;

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
            @JsonProperty("cottonPart") int cottonPart,
            @JsonProperty("quantity") int quantity) {

        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
