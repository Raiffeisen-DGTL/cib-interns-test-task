package com.example.socks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "socks", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_socks", sequenceName = "seq_socks", allocationSize = 1)
public class Socks {
    @Id
    @GeneratedValue(generator = "seq_socks", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String color;
    @Column(name = "cotton_part")
    private int cottonPart;
    @Column
    private int quantity;

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

}
