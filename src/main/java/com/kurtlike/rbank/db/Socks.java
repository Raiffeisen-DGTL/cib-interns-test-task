package com.kurtlike.rbank.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@ToString
@Table(name = "socks")
@Entity
@IdClass(SocksKey.class)
public class Socks {
    @Id
    @Getter
    @Setter
    private String color;

    @Id
    @Getter
    @Setter
    private int cottonpart;

    @Getter
    @Setter
    private int quantity;
}