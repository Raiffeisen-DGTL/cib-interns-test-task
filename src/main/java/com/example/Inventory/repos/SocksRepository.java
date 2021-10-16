package com.example.Inventory.repos;

import com.example.Inventory.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksRepository extends JpaRepository<SocksEntity, Integer> {
    SocksEntity findByColorAndCottonPart(String color, Integer cottonPart);
    List<SocksEntity> findByCottonPartGreaterThanAndColor(Integer cottonPart, String color);
    List<SocksEntity> findByCottonPartLessThanAndColor(Integer cottonPart, String color);
//    List<SocksEntity> findByColorAndCottonPart(String color, Integer cottonPart);
}
