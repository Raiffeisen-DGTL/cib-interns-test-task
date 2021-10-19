package com.example.socksservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private  Integer id;
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;
    private int quantity;

    public Sock(String color, int cotton_part, int quantity) {
        this.color = color;
        this.cottonPart = cotton_part;
        this.quantity = quantity;
    }
}
