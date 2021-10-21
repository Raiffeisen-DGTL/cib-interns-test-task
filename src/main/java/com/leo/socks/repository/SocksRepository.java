package com.leo.socks.repository;

import com.leo.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {
    SocksEntity getByColorAndCottonPart(String color, int cottonPart);

    List<SocksEntity> findSocksEntitiesByColorAndCottonPartIsGreaterThan(String color, int cottonPart);

    List<SocksEntity> findSocksEntitiesByColorAndCottonPartIsLessThan(String color, int cottonPart);

    List<SocksEntity> findSocksEntitiesByColorAndCottonPartEquals(String color, int cottonPart);
}
