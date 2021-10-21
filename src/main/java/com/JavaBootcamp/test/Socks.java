package com.JavaBootcamp.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@EnableAutoConfiguration
@Entity
public class Socks {
    private @Id @GeneratedValue long id;
    private int quantity;
    private String color;
    private byte cottonPart;

    Socks(){}

    Socks(int quantity, byte cottonPart, String color){
        this.quantity = quantity;
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Socks))
            return false;
        Socks socks= (Socks) o;
        return Objects.equals(this.id, socks.id) && Objects.equals(this.quantity, socks.quantity)
                && Objects.equals(this.cottonPart, socks.cottonPart) && Objects.equals(this.color, socks.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.quantity, this.cottonPart, this.color);
    }

    @Override
    public String toString() {
        return "Socka{" + "id=" + this.id + ", quantity='" + this.quantity + '\'' + ", cottonPart='" + this.cottonPart
                + '\'' + ", color='" + this.color + '\'' + '}';
    }
}
