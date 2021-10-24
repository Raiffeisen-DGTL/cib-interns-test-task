package com.example.cibinternstesttask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Data
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    @Column(name = "cotton_part")
    private Long cottonPart;

    private Long quantity;

    public Sock() {

    }

    public Sock(String color, Long cottonPart, Long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
