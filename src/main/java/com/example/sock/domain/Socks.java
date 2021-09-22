package com.example.sock.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "socks")
@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String color;
    @Column
    private Long cottonPart;
    @Column
    private Long quantity;

}
