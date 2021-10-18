package net.raiffaisen.interntest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "socks")
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "color")
    private String color;

    @Column(name = "cottonPart")
    private int cottonPart;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at",nullable = true)
    private String created_at;

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", color=" + color + ", cottonPart=" + cottonPart + ", quantity=" + quantity + " created_at= " + created_at + "]";
    }
}
