package ru.pkaranda.cibinternstesttask.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.pkaranda.cibinternstesttask.model.domain.OperationType;
import ru.pkaranda.cibinternstesttask.model.domain.SockColor;


@Data
@Builder
public class SocksTransactionDTO {

    private Long id;
    private SockColor color;
    private OperationType operation;
    private int cottonPart;
    private int quantity;
}
