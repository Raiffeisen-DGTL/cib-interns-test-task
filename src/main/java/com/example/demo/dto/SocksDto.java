package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class SocksDto {

    @NotNull
    private String color;

    @NotNull
    @Min(value = 0)
    @Max(100)
    private Long cottonPart;

    @NotNull
    @Min(value = 1)
    private Long quantity;

}
