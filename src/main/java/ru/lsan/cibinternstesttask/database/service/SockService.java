package ru.lsan.cibinternstesttask.database.service;

import ru.lsan.cibinternstesttask.database.entity.SockEntity;
import ru.lsan.cibinternstesttask.dto.SockDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

public interface SockService {

    SockEntity createGood(SockDto dto);

    SockEntity findByDto(SockDto dto);

    void incomeGoodCount(IncomeDto incomeDto);

    void outcomeGoodCount(OutcomeDto outcomeDto);

    long getGoodsCountBy(SockDto dto);

}
