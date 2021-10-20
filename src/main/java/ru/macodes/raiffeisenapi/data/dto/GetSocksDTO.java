package ru.macodes.raiffeisenapi.data.dto;

import lombok.Data;
import ru.macodes.raiffeisenapi.data.enums.SocksEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class GetSocksDTO {

    @NotEmpty
    @NotBlank
    private String color;

    private SocksEnum operation;

    @Min(1)
    private byte cottonPart;
}
