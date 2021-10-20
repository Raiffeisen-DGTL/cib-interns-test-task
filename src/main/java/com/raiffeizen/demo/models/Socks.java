package com.raiffeizen.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Socks{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(updatable = false, name = "color")
    private String color;

    @Min(0)
    @Max(100)
    @Column(updatable = false, name = "cotton_part")
    private int cottonPart;

    @Min(0)
    @Column(name = "quantity")
    private long quantity;
}
