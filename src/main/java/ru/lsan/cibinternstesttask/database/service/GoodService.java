package ru.lsan.cibinternstesttask.database.service;

import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.dto.GoodDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

public interface GoodService {

    GoodEntity createGood(GoodDto dto);

    GoodEntity findByDto(GoodDto dto);

    void incomeGoodCount(IncomeDto incomeDto);

    void outcomeGoodCount(OutcomeDto outcomeDto);

    long getGoodsCountBy(GoodDto dto);

}
