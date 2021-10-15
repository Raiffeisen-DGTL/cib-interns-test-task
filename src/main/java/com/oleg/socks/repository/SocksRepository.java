package com.oleg.socks.repository;

import com.oleg.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity,Long> {
    Optional<SocksEntity> findByColorAndCottonPart(String color, Integer cottonPart);

    Optional<List<SocksEntity>> findAllByColorAndCottonPartIsGreaterThan(String color, Integer cottonPart);

    Optional<List<SocksEntity>> findAllByColorAndCottonPartIsLessThan(String color, Integer cottonPart);
}

