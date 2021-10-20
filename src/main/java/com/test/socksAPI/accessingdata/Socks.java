package com.test.socksAPI.accessingdata;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
@IdClass(SocksPK.class)
public class Socks {


    @Id
    @Column(name = "color")
    private String color;

    @Id
    @Column(name = "cotton_part")
    private byte cottonPart;
    @Column(name = "quantity")
    private int quantity;


    public Socks() {}

    public Socks(String color, byte  cottonPart, int quantity) {

        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }



    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && quantity == socks.quantity  && color.equals(socks.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash( color, cottonPart, quantity);
    }

    @Override
    public String toString() {
        return "Socks{" +  ", color='" + this.color + '\'' + ", cottonPart='" + this.cottonPart + '\'' + '}';
    }
}
