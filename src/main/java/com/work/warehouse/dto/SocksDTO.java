package com.work.warehouse.dto;

import com.work.warehouse.entities.enums.EColor;
import lombok.Data;

@Data
public class SocksDTO {
    private Long id;
    private EColor color;
    private Integer cottonPart;
    private Integer quantity;
}
