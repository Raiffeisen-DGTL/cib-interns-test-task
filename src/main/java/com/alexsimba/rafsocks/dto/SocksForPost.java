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
    public class SocksForPost {
        @NotBlank
        private String color;
        @NotNull
        @Min(0)
        @Max(100)
        private Integer cottonPart;
        @NotNull
        @Min(1)
        private Integer quantity;
}
