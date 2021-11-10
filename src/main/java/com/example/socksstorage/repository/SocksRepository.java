package com.example.socksstorage.repository;

import com.example.socksstorage.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPartLessThan(String color, int cottonPart);
    List<Socks> findAllByColorAndCottonPart(String color, int cottonPart);
    int countAllByColorAndCottonPartGreaterThan(String color, int cottonPart);
    int countAllByColorAndCottonPartLessThan(String color, int cottonPart);
    int countAllByColorAndCottonPart(String color, int cottonPart);
}
