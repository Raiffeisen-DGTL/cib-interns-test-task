package com.davinchi.raifsocks.repos;

import com.davinchi.raifsocks.Socks;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<Socks,Long> {
    List<Socks> findByColor(String color);
    List<Socks> findByCottonPart(Integer cottonPart);
    List<Socks> findByColorAndCottonPart(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartIsGreaterThan(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartIsLessThan(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartIs(String color, Integer cottonPart);
}