package com.example.Socks.repository;

import com.example.Socks.model.Socks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepository extends CrudRepository<Socks, Integer> {
    List<Socks> findByColorAndCottonPartEquals(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartLessThan(String color, Integer cottonPart);
}
