package com.herokuapp.cibinternship.model;

import javax.persistence.*;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "socks")
@IdClass(SocksId.class)
public class Socks implements Serializable {

    @Id
    @NotNull
    private String color;

    @Id
    @Min(0) @Max(100)
    private int cottonPart;

    @Min(1)
    private long quantity;

    public Socks() {}
    public Socks(String color, int cottonPart, long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
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
        this.cottonPart = cottonPart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
