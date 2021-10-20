package com.viktor.cibinternstesttask.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "socks")
@Data
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String color;

    @Column
    @NotNull
    private Integer cottonPart;

    @Column
    @NotNull
    private Long quantity;
}
