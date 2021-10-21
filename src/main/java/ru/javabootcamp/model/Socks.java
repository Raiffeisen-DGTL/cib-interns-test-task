package ru.javabootcamp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private int cottonPart;
    @Column(nullable = false)
    private int quantity;

}
