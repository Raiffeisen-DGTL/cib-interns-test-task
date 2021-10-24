package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.SockEntity;
import ru.lsan.cibinternstesttask.database.repository.SockRepository;
import ru.lsan.cibinternstesttask.database.service.SockService;
import ru.lsan.cibinternstesttask.dto.SockDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

@Service
public class SockServiceImpl implements SockService {

    @Autowired
    private SockRepository sockRepository;

    @Override
    public SockEntity createGood(SockDto dto) {
        SockEntity entity = new SockEntity();
        entity.setColor(dto.getColor());
        entity.setCotton_part(dto.getCottonPart());
        entity.setStored(0);
        return sockRepository.save(entity);
    }

    @Override
    public SockEntity findByDto(SockDto dto) {
        return sockRepository.findByDto(dto.getColor(), dto.getCottonPart());
    }

    @Override
    public void incomeGoodCount(IncomeDto incomeDto) {
        sockRepository.incomeSockCount(incomeDto.getColor(), incomeDto.getCottonPart(), incomeDto.getQuantity());
    }

    @Override
    public void outcomeGoodCount(OutcomeDto outcomeDto) {
        sockRepository.outcomeSockCount(outcomeDto.getColor(), outcomeDto.getCottonPart(), outcomeDto.getQuantity());
    }

    @Override
    public long getGoodsCountBy(SockDto dto) {
        long answer = 0;
        if (dto.getOperation().equals("moreThan")) {
            answer = sockRepository.countMoreThan(dto.getColor(), dto.getCottonPart());
        }
        if (dto.getOperation().equals("lessThan")) {
            answer = sockRepository.countLessThan(dto.getColor(), dto.getCottonPart());
        }
        if (dto.getOperation().equals("equal")) {
            answer = sockRepository.countEquals(dto.getColor(), dto.getCottonPart());
        }
        return answer;
    }

}
