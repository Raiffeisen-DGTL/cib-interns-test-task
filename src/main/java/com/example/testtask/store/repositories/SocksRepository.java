package com.example.testtask.store.repositories;

import com.example.testtask.store.entities.SocksEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface SocksRepository extends JpaRepository<SocksEntity,Long> {



    Optional<List<SocksEntity>> findSocksEntitiesByCottonPartGreaterThanAndColorEquals(
        Integer cottonPart, String color);
    Optional<List<SocksEntity>> findSocksEntitiesByCottonPartLessThanAndColorEquals(Integer cottonPart, String color);

    Optional<List<SocksEntity>> findSocksEntitiesByCottonPartEqualsAndColorEquals(Integer cottonPart, String color);

    Optional<SocksEntity> findSocksEntityByColorAndCottonPart(String color, Integer cottonPart);


//    @Query("SELECT s FROM SocksEntity s " +
//            "WHERE s.color = :color " +
//            "AND s.cottonPart < :cottonPart")
//    List<SocksEntity> findAllByColorAndLessCottonPart(Optional<String> color, Optional<Integer> cottonPart);
//
//    @Query("SELECT s FROM SocksEntity s WHERE s.color = :color AND s.cottonPart > :cottonPart")
//    List<SocksEntity> findAllByColorAndMoreCottonPart(Optional<String> color, Optional<Integer> cottonPart);
//
//    @Query("SELECT s FROM SocksEntity s " +
//            "WHERE s.color = :color " +
//            "AND s.cottonPart = :cottonPart")
//    Optional<SocksEntity> findAllByColorAndEqualCottonPart(Optional<String> color, Optional<Integer> cottonPart);
}
