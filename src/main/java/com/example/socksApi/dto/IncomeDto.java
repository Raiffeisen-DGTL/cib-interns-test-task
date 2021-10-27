package com.example.socksApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {

    @NotNull
    private String color;

    @NotNull
    private Integer cottonPart;

    @NotNull
    private Integer amount;

}
