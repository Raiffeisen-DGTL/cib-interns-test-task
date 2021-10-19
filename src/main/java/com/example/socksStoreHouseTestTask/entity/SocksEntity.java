package com.example.socksStoreHouseTestTask.entity;

import javax.persistence.*;

@Entity
@Table(name = "socks")
public class SocksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private int cottonPart;
    private int quantity;

    public SocksEntity() {
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getQuantity() {
        return quantity;
    }

}
