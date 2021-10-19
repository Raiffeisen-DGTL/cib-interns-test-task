package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.database.repository.GoodRepository;
import ru.lsan.cibinternstesttask.database.service.GoodService;
import ru.lsan.cibinternstesttask.dto.GoodDto;
import ru.lsan.cibinternstesttask.dto.IncomeDto;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public GoodEntity createGood(GoodDto dto) {
        GoodEntity entity = new GoodEntity();
        entity.setColor(dto.getColor());
        entity.setCotton_part(dto.getCottonPart());
        entity.setStored(0);
        return goodRepository.save(entity);
    }

    @Override
    public GoodEntity findByDto(GoodDto dto) {
        return goodRepository.findByDto(dto.getColor(), dto.getCottonPart());
    }

    @Override
    public void incomeGoodCount(IncomeDto incomeDto) {
        goodRepository.incomeGoodCount(incomeDto.getColor(), incomeDto.getCottonPart(), incomeDto.getQuantity());
    }

    @Override
    public void outcomeGoodCount(OutcomeDto outcomeDto) {
        goodRepository.outcomeGoodCount(outcomeDto.getColor(), outcomeDto.getCottonPart(), outcomeDto.getQuantity());
    }

    @Override
    public long getGoodsCountBy(GoodDto dto) {
        long answer = 0;
        if (dto.getOperation().equals("moreThan")) {
            answer = goodRepository.countMoreThan(dto.getColor(), dto.getCottonPart());
        }
        if (dto.getOperation().equals("lessThan")) {
            answer = goodRepository.countLessThan(dto.getColor(), dto.getCottonPart());
        }
        if (dto.getOperation().equals("equals")) {
            answer = goodRepository.countEquals(dto.getColor(), dto.getCottonPart());
        }
        return answer;
    }

}
