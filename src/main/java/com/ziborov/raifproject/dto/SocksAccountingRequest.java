package com.ziborov.raifproject.dto;

import com.ziborov.raifproject.model.Socks;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SocksAccountingRequest {

    @NotNull(message = "Color should not be null")
    private Socks.SocksColor color;

    @NotNull(message = "Cotton part should not be null")
    @Min(value = 0, message = "Cotton part should be more than 0")
    @Max(value = 100, message = "Cotton part should be less than 100")
    private Integer cottonPart;

    @NotNull(message = "Quantity should not be null")
    @Min(value = 1, message = "Quantity must be positive and more then 0")
    private Integer quantity;

}