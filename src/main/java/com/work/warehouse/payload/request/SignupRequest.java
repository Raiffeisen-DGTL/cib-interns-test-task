package com.work.warehouse.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignupRequest {
    @NotEmpty(message = "Please enter color")
    private String color;
    @NotEmpty(message = "Please enter cotton")
    private Integer cottonPart;
    @NotEmpty(message = "Please enter quantity")
    private Integer quantity;
}
