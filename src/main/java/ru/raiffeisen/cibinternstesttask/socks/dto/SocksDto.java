package ru.raiffeisen.cibinternstesttask.socks.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Используется для получения данных в SocksController о количестве и типе носков.
 */
public record SocksDto(
        @NotBlank(message = "Color must not be blank")
        String color,

        @NotNull
        @Min(value = 0, message = "Cotton part must be greater or equal 0")
        @Max(value = 100, message = "Cotton part must be less or equal 100")
        Short cottonPart,

        @NotNull
        @Min(value = 1, message = "Quantity must be greater than 0")
        Integer quantity) {
}
