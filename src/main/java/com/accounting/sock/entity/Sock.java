package com.accounting.sock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sock")
public class Sock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column
    private String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "cottonpart")
    private int cottonPart;

    @NotNull
    @Min(value = 0)
    @Column
    private int quantity ;

    public Sock(String color, Integer cottonPart, Integer quantity) {

        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;

    }

}