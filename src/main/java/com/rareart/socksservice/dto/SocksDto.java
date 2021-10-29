package com.rareart.socksservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class SocksDto {
    @NotBlank(message = "the sock must have a color")
    private String color;

    @Min(value = 0, message = "cotton part of the socks must be greater or equals to 0")
    @Max(value = 100, message = "cotton part of the socks must be less or equals to 100")
    private byte cottonPart;

    @Min(value = 1, message = "quantity value of the socks can't be less than 1")
    private int quantity;
}
