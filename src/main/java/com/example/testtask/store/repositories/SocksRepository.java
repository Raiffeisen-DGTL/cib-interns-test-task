package com.example.testtask.store.repositories;

import com.example.testtask.store.entities.SocksEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface SocksRepository extends JpaRepository<SocksEntity,Long> {

    Stream<SocksEntity> streamFindByColor(String color);

    @Override
    List<SocksEntity> findAllById(Iterable<Long> longs);


    //List<SocksEntity> findAll(String color);

    //Optional<SocksEntity> findByColor(String color);
    //Stream<SocksEntity> streamAll();

    //Stream<SocksEntity> streamAllByCottonPart(Double cottonPart);

    @Query("SELECT s FROM SocksEntity s " +
            "WHERE s.color = :color " +
            "AND s.cottonPart < :cottonPart")
    List<SocksEntity> findAllByColorAndLessCottonPart(Optional<String> color, Optional<Integer> cottonPart);

    @Query("SELECT s FROM SocksEntity s WHERE s.color = :color AND s.cottonPart > :cottonPart")
    List<SocksEntity> findAllByColorAndMoreCottonPart(Optional<String> color, Optional<Integer> cottonPart);

    @Query("SELECT s FROM SocksEntity s " +
            "WHERE s.color = :color " +
            "AND s.cottonPart = :cottonPart")
    Optional<SocksEntity> findAllByColorAndEqualCottonPart(Optional<String> color, Optional<Integer> cottonPart);
}
