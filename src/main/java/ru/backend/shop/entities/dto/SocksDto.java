package ru.backend.shop.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SocksDto {

    private String color;

    private Integer cottonPart;

    private Integer quantity;
}