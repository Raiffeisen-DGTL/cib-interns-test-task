package com.task.raif.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность пары носков")
public class SocksDto {
    @Schema(description = "Цвет")
    @NotBlank(message = "Поле не должно быть пусто")
    String color;

    @Schema(description = "Содержание хлопка")
    @NotNull
    @Min(value = 0, message = "Содержание хлопка не может быть меньше 0%")
    @Max(value = 100, message = "Содержание хлопка не может превышать 100%")
    int cottonPart;

    @Schema(description = "Количество")
    @NotNull
    @Min(value = 0, message = "Количество не может быть отрицательным")
    int quantity;
}
