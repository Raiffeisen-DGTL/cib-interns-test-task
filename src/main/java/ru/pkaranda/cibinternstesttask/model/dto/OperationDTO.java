package ru.pkaranda.cibinternstesttask.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.pkaranda.cibinternstesttask.model.domain.OperationType;

@Data
@Builder
public class OperationDTO {

    private Long id;
    private OperationType operation;
}
