package ru.sillyseal.raiffeisentask.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
@IdClass(SockId.class)
public class Sock {
    @Id
    @Column(name = "color", nullable = false)
    private String color;

    @Id
    @Column(name = "cotton_part", nullable = false)
    private int cotton_part;

    @Column(name="quantity")
    private int quantity;

    public Sock() { }

    public Sock(String color, int cotton_part, int quantity) {
        this.color = color;
        this.cotton_part = cotton_part;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getCotton_part() {
        return cotton_part;
    }
    public void setCotton_part(int cotton_part) {
        this.cotton_part = cotton_part;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cotton_part == sock.cotton_part && color.equals(sock.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cotton_part);
    }
}