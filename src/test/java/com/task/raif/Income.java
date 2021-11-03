package com.task.raif;

public class Income {
    private String color;
    private String cottonPart;
    private String quantity;

    public Income(String color, String cottonPart, String quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Income() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(String cottonPart) {
        this.cottonPart = cottonPart;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
