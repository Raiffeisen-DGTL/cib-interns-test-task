package ru.danilarassokhin.raiffeisensocks.dto;

import ru.danilarassokhin.raiffeisensocks.model.SockColor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SockIncomeDto {

    @NotNull(message = "Socks color can't be null")
    @NotBlank(message = "Socks color can't be null")
    private SockColor sockColor;

    @Min(value = 0, message = "Cotton part must be greater or equal to zero")
    @Max(value = 100, message = "Cotton part must be less or equal to zero")
    private byte cottonPart;

    @Min(value = 1, message = "Socks quantity must be greater than zero")
    private long quantity;



}
