package com.example.testsocks.model;

import lombok.Data;
import javax.validation.constraints.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "socks")
@IdClass(SocksPK.class)
public class Socks {

    @Id
    @NotEmpty
    private String color;

    @Id
    @NotNull
    @Min(0)
    @Max(100)
    private int cottonPart;

    @NotNull
    @Min(1)
    private int quantity;
}
