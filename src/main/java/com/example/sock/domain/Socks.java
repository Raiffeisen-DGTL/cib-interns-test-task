package com.example.sock.domain;

import javax.persistence.*;

@Table(name = "socks")
@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String color;
    @Column
    private Long cottonPart;
    @Column
    private Long quantity;

    public Socks() {
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

    public Long getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Long cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
