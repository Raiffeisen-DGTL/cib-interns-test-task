package ru.raiffeisen.socks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.raiffeisen.socks.enums.Operation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocksRequestDto {
    @Min(value = 0, message = "Cotton percent must be a positive number")
    @Max(value = 100, message = "Cotton percent must be less then 100")
    @NotNull(message = "Cotton percent is a required parameter")
    private Integer cottonPart;

    @Pattern(regexp = "moreThan|equal|lessThan")
    @NotNull(message = "Operation is a required parameter")
    private String operation;

    @NotBlank(message = "Color can not be blank")
    @NotNull(message = "Color percent is a required parameter")
    private String color;
}
