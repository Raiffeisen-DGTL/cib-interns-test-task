package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.database.entity.IncomeEntity;
import ru.lsan.cibinternstesttask.database.repository.IncomeRepository;
import ru.lsan.cibinternstesttask.database.service.GoodService;
import ru.lsan.cibinternstesttask.database.service.IncomeService;
import ru.lsan.cibinternstesttask.dto.GoodDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;

import java.time.LocalDateTime;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private GoodService goodService;

    @Override
    public IncomeEntity createIncome(IncomeDto incomeDto) {
        IncomeEntity incomeEntity = new IncomeEntity();
        GoodDto goodDtoFromIncome = new GoodDto(incomeDto.getColor(), null, incomeDto.getCottonPart());
        GoodEntity goodEntity;

        goodEntity = goodService.findByDto(goodDtoFromIncome);
        if (goodEntity == null) {
            goodEntity = goodService.createGood(goodDtoFromIncome);
        }
        incomeEntity.setDate_time(LocalDateTime.now());
        incomeEntity.setGoodIncome(goodEntity);
        incomeEntity.setQuantity(incomeDto.getQuantity());
        goodService.incomeGoodCount(incomeDto);
        return incomeRepository.save(incomeEntity);
    }

}
