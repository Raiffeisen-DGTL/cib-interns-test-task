package com.viktor.cibinternstesttask.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class SockParamsDto {
    @NotBlank
    private String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Integer cottonPart;

    @NotBlank
    private String operation;
}
