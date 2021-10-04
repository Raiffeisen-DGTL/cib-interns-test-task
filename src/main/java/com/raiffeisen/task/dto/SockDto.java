package com.raiffeisen.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SockDto {
    private String color;
    private Integer cottonPart;
    private Integer quantity;
}
