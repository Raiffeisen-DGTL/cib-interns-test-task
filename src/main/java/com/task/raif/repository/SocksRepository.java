package com.task.raif.repository;

import com.task.raif.model.SocksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksModel, Integer> {
    Optional<SocksModel> findSocksModelsByColorAndCottonPart(String color, int cottonPart);
    List<SocksModel> findSocksModelsByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<SocksModel> findSocksModelsByColorAndCottonPartEquals(String color, int cottonPart);
    List<SocksModel> findSocksModelsByColorAndCottonPartLessThan(String color, int cottonPart);
}
