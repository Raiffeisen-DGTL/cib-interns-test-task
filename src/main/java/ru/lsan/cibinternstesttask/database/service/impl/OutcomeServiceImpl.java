package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.SockEntity;
import ru.lsan.cibinternstesttask.database.entity.OutcomeEntity;
import ru.lsan.cibinternstesttask.database.repository.OutcomeRepository;
import ru.lsan.cibinternstesttask.database.service.SockService;
import ru.lsan.cibinternstesttask.database.service.OutcomeService;
import ru.lsan.cibinternstesttask.dto.SockDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

import java.time.LocalDateTime;

@Service
public class OutcomeServiceImpl implements OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private SockService sockService;

    @Override
    public OutcomeEntity createOutcome(OutcomeDto outcomeDto) throws IllegalArgumentException{
        OutcomeEntity outcomeEntity = new OutcomeEntity();
        SockDto sockDtoFromOutcome = new SockDto(outcomeDto.getColor(), null, outcomeDto.getCottonPart());
        SockEntity sockEntity;

        sockEntity = sockService.findByDto(sockDtoFromOutcome);
        if (sockEntity == null) {
            sockEntity = sockService.createGood(sockDtoFromOutcome);
        }
        outcomeEntity.setDate_time(LocalDateTime.now());
        outcomeEntity.setSockOutcome(sockEntity);
        outcomeEntity.setQuantity(outcomeDto.getQuantity());
        if(outcomeDto.getQuantity()> sockEntity.getStored()){
            throw new IllegalArgumentException();
        }
        sockService.outcomeGoodCount(outcomeDto);
        return outcomeRepository.save(outcomeEntity);
    }

}
