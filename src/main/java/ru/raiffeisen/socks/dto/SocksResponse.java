package ru.raiffeisen.socks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApiModel("Ответ о количестве пар носков на складе")
public class SocksResponse {
    @ApiModelProperty("Количество пар носков")
    public int quantity;
}


