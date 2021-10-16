package com.example.Inventory.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "socks")
public class SocksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "color не может быть пустым")
    private String color;

    @NotNull(message = "cottonPart не может быть нулем")
    @Min(0)
    @Max(100)
    private Integer cottonPart;

    @NotNull(message = "quantity не может быть нулем")
//    @Size(min = 1, message = "quantity should be more than 0")
    @Min(1)
    private Integer quantity;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
