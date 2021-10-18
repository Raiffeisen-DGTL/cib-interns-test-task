package ru.lsan.cibinternstesttask.database.service;

import ru.lsan.cibinternstesttask.database.entity.GoodEntity;
import ru.lsan.cibinternstesttask.dto.GoodDto;

import java.util.List;

public interface GoodService {

    GoodEntity createGood(GoodDto dto);

    void delete(GoodEntity good);

    long getGoodsCountBy(GoodDto dto);
}
