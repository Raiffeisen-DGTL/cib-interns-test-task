package ru.lsan.cibinternstesttask.database.service;

import ru.lsan.cibinternstesttask.database.entity.OutcomeEntity;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

public interface OutcomeService {

    OutcomeEntity createOutcome(OutcomeDto outcomeDto);

}
