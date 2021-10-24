package com.task.raif.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String color;

    @Column
    private int cottonPart;

    @Column
    private int quantity;

    public SocksModel(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
