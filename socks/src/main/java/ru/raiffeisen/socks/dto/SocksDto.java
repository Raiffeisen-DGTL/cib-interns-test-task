package ru.raiffeisen.socks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocksDto {

    @Min(value = 0, message = "Cotton percent must be a positive number")
    @Max(value = 100, message = "Cotton percent must be less then 100")
    @NotNull(message = "Cotton percent is a required parameter")
    private Integer cottonPart;

    @Min(value = 0, message = "Quantity must be a positive number")
    @NotNull(message = "Quantity percent is a required parameter")
    private Long quantity;

    @NotBlank(message = "Color can not be blank")
    @NotNull(message = "Color percent is a required parameter")
    private String color;

}
