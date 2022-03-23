package com.zpsx.cibinternstesttask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Getter@Setter
@NoArgsConstructor
public class SockStock {
    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String color;

    @Min(0) @Max(100)
    private Byte cottonPart;

    private Long quantity;

    public SockStock(String color, Byte cottonPart, Long quantity){
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
