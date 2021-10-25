package ru.raiffeisen.socks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import ru.raiffeisen.socks.dto.validation.SockOperationConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@ApiModel("Запрос на получение количества носков")
public class SocksQuantityRequest {
    @NotNull
    @NotBlank
    @ApiModelProperty("Цвет носков")
    public String color;

    @SockOperationConstraint
    @ApiModelProperty("Оператор сравнения значения количества хлопка в составе носков")
    public String operation;

    @Min(0)
    @Max(100)
    @ApiModelProperty("Количество хлопка")
    public int cottonPart;
}