package com.raiffeisen.socks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SockDto {
    @JsonProperty("color")
    private String color;
    @JsonProperty("cottonPart")
    private Integer cottonPart;
    @JsonProperty("quantity")
    private Integer quantity;
}
