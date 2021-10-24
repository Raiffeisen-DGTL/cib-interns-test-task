package com.raif.model;

import javax.persistence.*;

@Entity
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // socks cannot be colorless
    @Column(nullable = false)
    private String color;

    // each sock must have it's own cotton part so this field can't be null
    @Column(nullable = false)
    private Integer cottonPart;

    public Sock(String color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public Sock() {
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
}
