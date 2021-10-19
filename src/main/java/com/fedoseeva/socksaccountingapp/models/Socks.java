package com.fedoseeva.socksaccountingapp.models;

public class Socks {

    private String color;

    private int cottonPart;

    private int quantity;


    public Socks(String color, int cottonPart, int quantity) {
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

}
