package com.warehouse.storewarehouse.counting;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Value
public class DeliveryBatchSocks {

    @NotBlank
    String color;
    @Min(0) @Max(100)
    Integer cottonPart;
    @Positive
    Integer quantity;

}
