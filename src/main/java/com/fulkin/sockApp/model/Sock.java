package com.fulkin.sockApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Fulkin
 * Created on 20.10.2021
 */

@Entity
@Table(name = "sock")
public class Sock {
    @Id
    @Column(name = "color", unique = true)
    private String color;

    @Column(name = "cotton_part", unique = true)
    private int cottonPart;

    @Column(name = "quantity")
    private int quantity;

    public Sock(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Sock() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonPart == sock.cottonPart && color.equals(sock.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }

    @Override
    public String toString() {
        return "color= " + color +
                ", cottonPart= " + cottonPart;
    }
}
