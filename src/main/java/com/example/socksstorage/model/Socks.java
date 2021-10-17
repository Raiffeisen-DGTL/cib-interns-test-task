package com.example.socksstorage.model;

import javax.persistence.*;

@Entity
@Table(name = "socks")
public class Socks {

    public enum Operation {
        moreThan,
        lessThan,
        equal
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;

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
}
