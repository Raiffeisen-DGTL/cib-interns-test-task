package ru.samusev.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SocksRequest {
    private String color;
    private Integer cottonPart;
    private Long quantity;
}
