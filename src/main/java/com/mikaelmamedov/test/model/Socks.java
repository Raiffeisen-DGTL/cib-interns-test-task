package com.mikaelmamedov.test.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int cottonPart;
    private String color;
    private int quantity;

    public Socks() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
