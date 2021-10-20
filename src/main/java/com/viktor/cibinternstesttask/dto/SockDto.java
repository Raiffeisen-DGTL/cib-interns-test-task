package com.viktor.cibinternstesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SockDto {
    @NotBlank
    private String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Integer cottonPart;

    @NotNull
    @Min(value = 0L)
    private Long quantity;
}
