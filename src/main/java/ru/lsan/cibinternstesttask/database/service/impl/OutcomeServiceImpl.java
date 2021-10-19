package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.database.entity.OutcomeEntity;
import ru.lsan.cibinternstesttask.database.repository.OutcomeRepository;
import ru.lsan.cibinternstesttask.database.service.GoodService;
import ru.lsan.cibinternstesttask.database.service.OutcomeService;
import ru.lsan.cibinternstesttask.dto.GoodDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

import java.time.LocalDateTime;

@Service
public class OutcomeServiceImpl implements OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private GoodService goodService;

    @Override
    public OutcomeEntity createOutcome(OutcomeDto outcomeDto) throws IllegalArgumentException{
        OutcomeEntity outcomeEntity = new OutcomeEntity();
        GoodDto goodDtoFromOutcome = new GoodDto(outcomeDto.getColor(), null, outcomeDto.getCottonPart());
        GoodEntity goodEntity;

        goodEntity = goodService.findByDto(goodDtoFromOutcome);
        if (goodEntity == null) {
            goodEntity = goodService.createGood(goodDtoFromOutcome);
        }
        outcomeEntity.setDate_time(LocalDateTime.now());
        outcomeEntity.setGoodOutcome(goodEntity);
        outcomeEntity.setQuantity(outcomeDto.getQuantity());
        if(outcomeDto.getQuantity()>goodEntity.getStored()){
            throw new IllegalArgumentException();
        }
        goodService.outcomeGoodCount(outcomeDto);
        return outcomeRepository.save(outcomeEntity);
    }

}
