package com.oleg.socks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Приход и отпуск носков")
public class SocksDto {
    @Schema(description = "Цвет носков")
    String color;
    @Schema(description = "Процентное содержание хлопка")
    String cottonPart;
    @Schema(description = "Количество пар носков")
    String quantity;
}
