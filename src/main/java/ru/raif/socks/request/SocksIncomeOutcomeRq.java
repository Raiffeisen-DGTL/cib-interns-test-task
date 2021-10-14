package ru.raif.socks.request;

import lombok.Value;

import javax.validation.constraints.*;

@Value
public class SocksIncomeOutcomeRq {

    @NotBlank
    @Size(min = 1, max = 50)
    String color;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    Integer cottonPart;

    @NotNull
    @Min(value = 1)
    Integer quantity;
}
