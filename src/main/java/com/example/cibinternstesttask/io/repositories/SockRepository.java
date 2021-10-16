package com.example.cibinternstesttask.io.repositories;

import com.example.cibinternstesttask.io.entities.SockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<SockEntity, Long> {
    SockEntity findByColorAndCottonPart(String color, int cottonPart);
    List<SockEntity> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<SockEntity> findAllByColorAndCottonPartLessThan(String color, int cottonPart);
    List<SockEntity> findAllByColorAndCottonPartEquals(String color, int cottonPart);
}
