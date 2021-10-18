package ru.raiffeisen.socksforraif.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SocksJson {

    private String color;

    private Byte cottonPart;

    private Integer quantity;

}
