package ru.raiffeisen.socks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApiModel("Ошибка")
public class ErrorResponse {
    @ApiModelProperty("Информация об ошибке")
    public String message;
}
