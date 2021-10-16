package com.raiffeisen.socks.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Data
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String color;
    @Column
    private Integer cottonPart;
    @Column
    private Integer quantity;
}
