package com.rareart.socksservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class SocksParamsRequest {
    private final String color;
    private final String operation;

    @Min(value = 0, message = "cotton part of the socks must be greater or equals to 0")
    @Max(value = 100, message = "cotton part of the socks must be less or equals to 100")
    private Byte cottonPart;
}
