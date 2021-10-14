package ru.pkaranda.cibinternstesttask.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.pkaranda.cibinternstesttask.model.domain.TransactionType;

@Data
@Builder
public class TransactionDTO {

    private Long id;
    private TransactionType type;
}
