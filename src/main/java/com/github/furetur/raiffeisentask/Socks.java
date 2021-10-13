package com.github.furetur.raiffeisentask;

import javax.persistence.*;

@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String color;

    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;

    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Socks() {}

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
