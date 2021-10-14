package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@Builder
public class SocksTransaction {

    private Long id;
    private SockColor color;
    private OperationType operation;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @Min(0)
    private int quantity;

}
