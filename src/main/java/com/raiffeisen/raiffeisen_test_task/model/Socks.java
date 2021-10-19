package com.raiffeisen.raiffeisen_test_task.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String color;
    @Column
    private byte cottonPart;
    @Column
    private long quantity;
}
