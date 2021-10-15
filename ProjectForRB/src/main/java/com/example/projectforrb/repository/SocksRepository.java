package com.example.projectforrb.repository;

import com.example.projectforrb.model.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SocksRepository extends JpaRepository<SocksEntity, UUID> {
    Integer countAllByColorEqualsAndCottonPartIsGreaterThan(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
    Integer countAllByColorEqualsAndCottonPartIsLessThan(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
    Integer countAllByColorEqualsAndCottonPartEquals(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
    Optional<SocksEntity> findByColorAndCottonPart(@Param("color") String color,@Param("cottonPart") Integer cottonPart);
}

