package com.vadim01er.taskraiffeisenbank.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "socks")
@ToString
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String color;

    @Column
    private Short cottonPart;

    @Column
    private Long quality;
}
