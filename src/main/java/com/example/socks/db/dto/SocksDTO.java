package com.example.socks.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SocksDTO {

    @NotBlank(message = "Color is mandatory")
    private String color;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @NotNull
    @Min(1)
    private int quantity;

}
