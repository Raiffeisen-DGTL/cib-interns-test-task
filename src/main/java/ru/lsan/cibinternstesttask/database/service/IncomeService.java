package ru.lsan.cibinternstesttask.database.service;

import ru.lsan.cibinternstesttask.database.entity.IncomeEntity;
import ru.lsan.cibinternstesttask.dto.IncomeDto;

public interface IncomeService {

    IncomeEntity createIncome(IncomeDto incomeDto);

}
