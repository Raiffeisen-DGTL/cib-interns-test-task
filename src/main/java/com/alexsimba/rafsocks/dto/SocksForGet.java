package com.alexsimba.rafsocks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocksForGet {
    @NotBlank
    private String color;
    @NotBlank
    private String operation;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer cottonPart;

}
