package ru.pkaranda.cibinternstesttask.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pkaranda.cibinternstesttask.model.domain.SockColor;
import ru.pkaranda.cibinternstesttask.model.domain.Transaction;


@Data
@NoArgsConstructor
public class SocksTransactionDTO {

    private Long id;
    private SockColor color;
    private Transaction transactionType;
    private int cottonPart;
    private int quantity;
}
