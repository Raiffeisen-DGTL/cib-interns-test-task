package ru.testcase.accountingSocks.model;

import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "depossocks")
@ToString
public class Socks  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "cottonpart", nullable = false)
    private int cottonpart;

    @Column(name = "quantity",nullable = false)
    private int quantity;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonpart;
    }

    public void setCottonPart(int cottonpart) {
        this.cottonpart = cottonpart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}