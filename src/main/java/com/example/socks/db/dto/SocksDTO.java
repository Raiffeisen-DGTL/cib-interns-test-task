package com.example.socks.db.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Schema(description = "Сущность носков")
public class SocksDTO {

    @NotBlank(message = "Color is mandatory")
    @Schema(description = "Цвет носков")
    private String color;

    @Min(0)
    @Max(100)
    @Schema(description = "Значение процента хлопка в составе носков.")
    private int cottonPart;

    @NotNull
    @Min(1)
    @Schema(description = "количество пар носков, целое число больше 0")
    private int quantity;

}
