package com.dragulaxis.raiffeisentest;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "color")
    private String color;
    @Column(name = "cotton_part")
    private byte cottonPart;
    @Column(name = "quantity")
    private int quantity;

    Socks (String color, byte cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "color=" + color +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Socks socks = (Socks) o;
        return this.getColor().equals(socks.getColor()) && this.getCottonPart() == socks.getCottonPart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart, quantity);
    }

    public Socks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}