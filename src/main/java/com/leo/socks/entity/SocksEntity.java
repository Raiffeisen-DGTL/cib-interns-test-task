package com.leo.socks.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "socks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
    private int cottonPart;
    private int quantity;
}
