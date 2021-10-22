package ru.pkaranda.cibinternstesttask.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pkaranda.cibinternstesttask.model.domain.TransactionType;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private TransactionType type;
}
