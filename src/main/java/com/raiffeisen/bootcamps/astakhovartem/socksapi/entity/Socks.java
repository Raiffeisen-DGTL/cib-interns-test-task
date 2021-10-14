package com.raiffeisen.bootcamps.astakhovartem.socksapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private long id;

    @NotBlank
    @Column(name = "color")
    private String color;

    @Min(0)
    @Max(100)
    @Column(name = "cottonPart")
    private Integer cottonPart;

    @Min(0)
    @Column(name = "quantity")
    private Integer quantity;
}
