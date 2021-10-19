package com.java.task.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SocksByParamsRequest {
    @NotBlank
    private String color;
    @NotBlank
    private String operation;
    @NotNull
    @Min(0)
    @Max(100)
    private int cottonPart;
}
