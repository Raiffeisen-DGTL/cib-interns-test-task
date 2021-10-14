package com.example.company.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(schema = "PUBLIC")
@Entity
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "color", nullable = false)
    private String color;
    @NotNull

    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    @NotNull
    private int quantity;
}
