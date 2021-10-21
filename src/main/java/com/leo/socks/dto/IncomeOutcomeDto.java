package com.leo.socks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeOutcomeDto {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String color;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer cottonPart;
    @NotNull
    @Min(1)
    private Integer quantity;
}
