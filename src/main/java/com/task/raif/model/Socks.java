package com.task.raif.model;

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
public class Socks {
    @NotBlank(message = "Поле не должно быть пусто")
    String color;

    @NotNull
    @Min(value = 0, message = "Содержание хлопка не может быть меньше 0%")
    @Max(value = 100, message = "Содержание хлопка не может превышать 100%")
    int cottonPart;

    @NotNull
    @Min(value = 0, message = "Количество не может быть отрицательным")
    int quantity;
}
