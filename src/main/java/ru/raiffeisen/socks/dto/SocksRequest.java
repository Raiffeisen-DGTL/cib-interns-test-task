package ru.raiffeisen.socks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@ApiModel("Запрос на приходование и отпуск пары носков")
public class SocksRequest {
    @NotBlank
    @NotNull
    @ApiModelProperty("Цвет носков")
    public String color;

    @Min(0)
    @Max(100)
    @ApiModelProperty("Процентное содержание хлопка в составе носков")
    public int cottonPart;

    @Min(1)
    @ApiModelProperty("Количество пар носков")
    public int quantity;
}