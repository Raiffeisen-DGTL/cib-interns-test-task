package com.raiffeisen.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SockDto {
    private String color;
    @Min(value = 0, message = "Warning! Cotton part should be more than 0%")
    @Max(value = 100, message = "Warning! Cotton part should be less or equal than 100%")
    private Integer cottonPart;
    @Min(value = 1, message = "Warning! The quantity cannot be less than 1")
    private Integer quantity;
}
