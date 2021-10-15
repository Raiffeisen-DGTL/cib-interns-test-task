package ru.raiff.raiffsocksstore.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocksCategoryDto {

    @ApiModelProperty(name = "Цвет", required = true)
    private String color;

    @ApiModelProperty(name = "Содержание хлопка", required = true)
    private Short cottonPart;
}