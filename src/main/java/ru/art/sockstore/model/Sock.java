package ru.art.sockstore.model;

import java.util.Objects;

public class Sock {
    private Integer id;
    private String color;
    private Integer cottonPart;
    private Integer quantity;

    public Sock() {
    }

    public Sock(Integer id, String color, Integer cottonPart, Integer quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public static boolean isGood(Sock sock) {
        return sock.color != null &&
                sock.quantity >= 0 &&
                sock.cottonPart >= 0 &&
                sock.cottonPart <= 100;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return color.equals(sock.color) && cottonPart.equals(sock.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }

    @Override
    public String toString() {
        return "Sock{" + "id=" + id + ", color='" + color +
                '\'' + ", cottonPart='" + cottonPart +
                '\'' + ", quantity='" + quantity + '\'' + '}';
    }
}
