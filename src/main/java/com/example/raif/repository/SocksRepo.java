package com.example.raif.repository;

import com.example.raif.entity.SocksEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<SocksEntity, Long> {

    List<SocksEntity> findAll();

    List<SocksEntity> findByColorAndCottonPartGreaterThan(String color, int cottonPart);

    List<SocksEntity> findByColorAndCottonPartLessThan(String color, int cottonPart);

    List<SocksEntity> findByColorAndCottonPartEquals(String color, int cottonPart);

    SocksEntity findByColorAndCottonPart (String color, int cottonPart);
}
