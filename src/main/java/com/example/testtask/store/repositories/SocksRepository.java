package com.example.testtask.store.repositories;

import com.example.testtask.store.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {

  Optional<List<SocksEntity>> findSocksEntitiesByCottonPartGreaterThanAndColorEquals(
      Integer cottonPart, String color);

  Optional<List<SocksEntity>> findSocksEntitiesByCottonPartLessThanAndColorEquals(
      Integer cottonPart, String color);

  Optional<List<SocksEntity>> findSocksEntitiesByCottonPartEqualsAndColorEquals(Integer cottonPart,
      String color);

  Optional<SocksEntity> findSocksEntityByColorAndCottonPart(String color, Integer cottonPart);

}
