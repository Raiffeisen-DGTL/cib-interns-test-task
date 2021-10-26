package ru.raiffeisen.socks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.raiffeisen.socks.enums.Operation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocksRequestDto {
    @Min(value = 0, message = "Cotton percent must be a positive number")
    @Max(value = 100, message = "Cotton percent must be less then 100")
    private Integer cottonPart;

    private Operation operation;

    @NotBlank(message = "Color can not be blank")
    private String color;
}
