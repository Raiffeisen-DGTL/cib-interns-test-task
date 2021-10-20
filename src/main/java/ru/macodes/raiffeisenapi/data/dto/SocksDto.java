package ru.macodes.raiffeisenapi.data.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SocksDto {

    @NotBlank
    @NotEmpty
    private String color;

    @Min(1)
    @Max(100)
    private byte cottonPart;

    @Min(1)
    private  int quantity;

}
