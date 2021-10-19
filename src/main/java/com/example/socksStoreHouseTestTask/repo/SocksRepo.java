package com.example.socksStoreHouseTestTask.repo;


import com.example.socksStoreHouseTestTask.entity.SocksEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<SocksEntity, Long> {
        SocksEntity findAllByColorAndCottonPart(String color, Integer cottonPart);
        List<SocksEntity> findAllByColorAndCottonPartIsLessThan(String color, Integer cottonPart);
        List<SocksEntity> findAllByColorAndCottonPartEquals(String color, Integer cottonPart);
        List<SocksEntity> findAllByColorAndCottonPartIsGreaterThan(String color, Integer cottonPart);
}
