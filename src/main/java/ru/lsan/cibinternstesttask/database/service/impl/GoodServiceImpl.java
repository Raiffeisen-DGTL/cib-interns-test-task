package ru.lsan.cibinternstesttask.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.database.repository.GoodRepository;
import ru.lsan.cibinternstesttask.database.service.GoodService;
import ru.lsan.cibinternstesttask.dto.GoodDto;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public GoodEntity createGood(GoodDto dto) {
        GoodEntity entity = new GoodEntity();
        entity.setColor(dto.getColor());
        entity.setCottonPart(dto.getCottonPart());
        return goodRepository.save(entity);
    }

    @Override
    public void delete(GoodEntity good) {
        goodRepository.delete(good);
    }

    @Override
    public long getGoodsCountBy(GoodDto dto) {
        long answer = 0;
        if(dto.getOperation().equals("moreThan")){
            answer = goodRepository.countMoreThan(dto.getColor(),dto.getCottonPart());
        }
        return answer;
    }

}
