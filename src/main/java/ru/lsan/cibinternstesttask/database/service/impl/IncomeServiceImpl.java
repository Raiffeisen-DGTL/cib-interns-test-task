package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.SockEntity;
import ru.lsan.cibinternstesttask.database.entity.IncomeEntity;
import ru.lsan.cibinternstesttask.database.repository.IncomeRepository;
import ru.lsan.cibinternstesttask.database.service.SockService;
import ru.lsan.cibinternstesttask.database.service.IncomeService;
import ru.lsan.cibinternstesttask.dto.SockDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;

import java.time.LocalDateTime;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private SockService sockService;

    @Override
    public IncomeEntity createIncome(IncomeDto incomeDto) {
        IncomeEntity incomeEntity = new IncomeEntity();
        SockDto sockDtoFromIncome = new SockDto(incomeDto.getColor(), null, incomeDto.getCottonPart());
        SockEntity sockEntity;

        sockEntity = sockService.findByDto(sockDtoFromIncome);
        if (sockEntity == null) {
            sockEntity = sockService.createGood(sockDtoFromIncome);
        }
        incomeEntity.setDate_time(LocalDateTime.now());
        incomeEntity.setSockIncome(sockEntity);
        incomeEntity.setQuantity(incomeDto.getQuantity());
        sockService.incomeGoodCount(incomeDto);
        return incomeRepository.save(incomeEntity);
    }

}
