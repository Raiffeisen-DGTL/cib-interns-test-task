package com.home.sock.dto;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SocksIncomeOutcomeDto  {
    @NotBlank(message = "provide color")
    String color;

    @Min(0)
    @Max(100)
    @NotNull(message = "provide cottonPart")
    Integer cottonPart;

    @Min(1)
    @NotNull(message = "provide quantity")
    int quantity;

}
