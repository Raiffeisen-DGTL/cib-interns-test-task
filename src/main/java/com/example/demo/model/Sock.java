package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "socks")
public class Sock {

    @Id
    @GeneratedValue
    private long id;

    private String color;

    private int cottonPart;

    private int quantity;

    public Sock() {
    }

    public Sock(String color, int cottonPart, int quantity) {
        this.color = color;
        this.setCottonPart(cottonPart);
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        if (cottonPart <= 100 && cottonPart >= 0) {
            this.cottonPart = cottonPart;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return color + cottonPart + quantity;
    }
}
