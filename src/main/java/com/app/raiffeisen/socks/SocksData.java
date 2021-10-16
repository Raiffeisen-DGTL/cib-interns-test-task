package com.app.raiffeisen.socks;

public class SocksData {

    private String color;
    private int cottonPart;
    private int quantity;

    public SocksData(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
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

    public void setOutcome() {
        quantity *= -1;
    }

}
