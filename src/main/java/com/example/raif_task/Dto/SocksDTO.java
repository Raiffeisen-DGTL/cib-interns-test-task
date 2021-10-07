package com.example.raif_task.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SocksDTO {

    @JsonProperty
    private String color;
    @JsonProperty
    private String operation;
    @JsonProperty
    private double cottonPart;

}
