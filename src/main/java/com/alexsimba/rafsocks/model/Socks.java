package com.alexsimba.rafsocks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @NotBlank
    @Column(name = "color")
    private String color;
    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "cotton_part")
    private Integer cottonPart;

    public Socks(String color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

}


