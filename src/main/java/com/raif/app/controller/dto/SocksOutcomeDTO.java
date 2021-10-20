package com.raif.app.controller.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SocksOutcomeDTO {
    @NotBlank
    @ApiModelProperty("цвет носков, строка")
    private String color;

    @NotNull
    @Min(0)
    @Max(100)
    @ApiModelProperty("процентное содержание хлопка в составе носков, целое число от 0 до 100")
    private Integer cottonPart;

    @ApiModelProperty("Количество пар носков, целое число больше 0")
    @NotNull
    @Min(0)
    private Long quantity;
}
