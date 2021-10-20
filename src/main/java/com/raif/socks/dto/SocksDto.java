package com.raif.socks.dto;

import lombok.Value;
import org.springframework.lang.NonNull;

@Value
public class SocksDto {

    @NonNull
    String color;

    @NonNull
    Integer cottonPart;

    @NonNull
    Integer quantity;

}
