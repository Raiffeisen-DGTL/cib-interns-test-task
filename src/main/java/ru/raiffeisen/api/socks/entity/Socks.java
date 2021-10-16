package ru.raiffeisen.api.socks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Getter
@Setter
@NoArgsConstructor
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;

    @Column(name = "quantity")
    private int quantity;

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
