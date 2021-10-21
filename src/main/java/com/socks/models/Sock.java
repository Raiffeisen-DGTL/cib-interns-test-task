package com.socks.models;

public class Sock {
    private String color;
    private int cottonPart;
    private int quantity;

    public Sock() {
    }

    public Sock(String color, int cotonPart, int quantity) {
        this.color = color;
        this.cottonPart = cotonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
