package com.lazarev.socksapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SockDto {

    @NotEmpty(message = "Key 'color' should not be empty")
    @NotBlank(message = "Key 'color' should not be blank")
    @NotNull(message = "Key 'color' should be specified")
    @Pattern(regexp = "[A-Za-zА-Яа-я]+", message = "Key 'color' should contain only letters")
    private String color;

    @NotNull(message = "Key 'cottonPart' should be specified")
    @Min(value = 0, message = "Key 'cottonPart' should be in range from 0 to 100")
    @Max(value = 100, message = "Key 'cottonPart' should be in range from 0 to 100")
    private Integer cottonPart;

    @NotNull(message = "Key 'quantity' should be specified")
    @Min(value = 0, message = "Key 'quantity' should be at least 0")
    private Integer quantity;
}
