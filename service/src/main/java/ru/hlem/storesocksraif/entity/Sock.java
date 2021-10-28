package ru.hlem.storesocksraif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sock")
public class Sock {
    @Id
    @Column(name = "sock_id")
    private int id;
    @NotEmpty(message = "")
    @Column(name = "color")
    private String color;
    @NotNull
    @Min(value = 0, message = "")
    @Max(value = 100, message = "")
    @Column(name = "cotton_part")
    private Integer cottonPart;
    @NotNull
    @Min(value = 1, message = "")
    @Column(name = "quantity")
    private int quantity;

    public Sock() {
    }

    public Sock(int id, String color, int cottonPart, int quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
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

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cotton_part=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
