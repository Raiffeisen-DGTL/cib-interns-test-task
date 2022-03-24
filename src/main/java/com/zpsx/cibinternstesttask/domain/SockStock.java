package com.zpsx.cibinternstesttask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

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

    @PositiveOrZero
    private Long quantity;
}
