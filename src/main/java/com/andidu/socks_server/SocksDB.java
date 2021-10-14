package com.andidu.socks_server;

import javax.persistence.*;

@Entity
public class SocksDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String color;
    private Integer cottonPart;
    private Integer quantity;

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
}