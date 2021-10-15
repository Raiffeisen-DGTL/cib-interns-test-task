package com.example.projectforrb.service;

import com.example.projectforrb.model.SocksEntity;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SocksService {
    Integer countAllByColorEqualsAndCottonPartIsGreaterThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);
    Integer countAllByColorEqualsAndCottonPartIsLessThan(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
    Integer countAllByColorEqualsAndCottonPartEquals(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
    Optional<SocksEntity> findByColorAndCottonPart(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
}
