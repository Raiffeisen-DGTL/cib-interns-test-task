package com.example.socks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SockModel {

    @NotNull
    @JsonProperty(value = "color")
    private String color;

    @NotNull
    @JsonProperty(value = "cotton_part")
    private int cottonPart;

    @NotNull
    @JsonProperty(value = "quantity")
    private int quantity;

}
