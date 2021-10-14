package com.korolev.rest_raif.domain;

import javax.persistence.*;

@Entity
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String color;

    private Integer cottonPart;

    private Integer quantity;

    private boolean operation;


    public Integer getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }

    public Sock() {
    }

    public Sock(String color, Integer cottonPart, Integer quantity, boolean operation) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.operation = operation;
    }
}
