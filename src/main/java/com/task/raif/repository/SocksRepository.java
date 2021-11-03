package com.task.raif.repository;

import com.task.raif.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Optional<Socks> findSocksModelsByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findSocksModelsByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<Socks> findSocksModelsByColorAndCottonPartEquals(String color, int cottonPart);
    List<Socks> findSocksModelsByColorAndCottonPartLessThan(String color, int cottonPart);
}
