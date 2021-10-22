package com.example.socks2.entity;

import javax.persistence.*;

@Entity
public class SocksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    private String color;
    private Long cottonPart;
    private Long quantity;

    public SocksEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public Long getCottonPart() {
        return cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void addQuantity(Long quantity){
        this.quantity += quantity;
    }
    public void subtractQuantity(Long quantity){
        this.quantity -= quantity;
    }
}
