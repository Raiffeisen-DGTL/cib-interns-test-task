package com.leo.socks.dto;

import com.leo.socks.enums.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GettingByParamsDto {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String color;
    @NotNull
    private Operation operation;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer cottonPart;
}
