package com.raiffeisen.stocktaking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class SocksModelDTO {
    @NotBlank
    private String color;

    @Min(1)
    @Max(100)
    private int cottonPart;

    @PositiveOrZero
    private int quantity;
}
