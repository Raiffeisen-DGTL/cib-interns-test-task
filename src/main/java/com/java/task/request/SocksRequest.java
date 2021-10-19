package com.java.task.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksRequest {
    @NotBlank
    private String color;
    @NotNull
    @Min(0)
    @Max(100)
    private int cottonPart;
    @NotNull
    @Min(0)
    private int quantity;
}
