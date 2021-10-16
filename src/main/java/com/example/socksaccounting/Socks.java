package com.example.socksaccounting;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Socks{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String color;

    private Byte cottonPart;

    private Integer quantity;

    public Socks() {
    }

    public Socks(String color, Byte cottonPart, Integer quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
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

    public Byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
