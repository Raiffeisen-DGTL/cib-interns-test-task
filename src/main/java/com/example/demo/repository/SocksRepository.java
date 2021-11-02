package com.example.demo.repository;

import com.example.demo.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    List<Socks> findByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPartLessThan(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);
    Boolean existsByColorAndCottonPart(String color, int cottonPart);
}