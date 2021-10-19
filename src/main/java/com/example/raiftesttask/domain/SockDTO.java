package com.example.raiftesttask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
@AllArgsConstructor
public class SockDTO {

    @Min(0)
    @Max(100)
    public Integer cottonPart;
    public Color color;
    @PositiveOrZero
    public Long quantity;
}
