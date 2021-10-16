package com.example.cibinternstesttask.ui.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SocksDetailsRequest {

    @NotBlank(message = "Color is mandatory")
    private String color;

    @Min(value = 0, message = "Cotton part should not be less than 0")
    @Max(value = 100, message = "Cotton part should not be greater than 100")
    private int cottonPart;

    @Min(value = 1, message = "Quantity should not be less than 1")
    private long quantity;
}
