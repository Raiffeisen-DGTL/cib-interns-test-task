package com.example.socks2.repository;

import com.example.socks2.entity.SocksEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends CrudRepository<SocksEntity, Long> {
    Optional<SocksEntity> findByColorAndCottonPart(String color, Long cottonPart);

    List<SocksEntity> findAllByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);

    List<SocksEntity> findAllByColorAndCottonPartIsLessThan(String color, Long cottonPart);
}
