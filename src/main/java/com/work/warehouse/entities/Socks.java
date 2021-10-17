package com.work.warehouse.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SOCKS")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "Color", length = 64, updatable = false)
    private String color;
    @Column(name = "CottonPart", updatable = false)
    private Integer cottonPart;
    @Column(name = "Quantity")
    private Integer quantity;
}