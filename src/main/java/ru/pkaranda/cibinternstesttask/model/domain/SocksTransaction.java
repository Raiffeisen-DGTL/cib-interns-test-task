package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@Builder
public class SocksTransaction {

    private Long id;

    private Long colorId;
    private SockColor color;

    private Long transactionTypeId;
    private Transaction transactionType;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @Min(0)
    private int quantity;

}
