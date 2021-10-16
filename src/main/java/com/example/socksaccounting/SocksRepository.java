package com.example.socksaccounting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SocksRepository extends CrudRepository<Socks, Integer> {
    Socks findByColorAndCottonPart(String color, Byte cottonPart);

    @Query("SELECT s from Socks s where s.color = ?1 and s.cottonPart < ?2")
    List<Socks> findByColorAndCottonPartLessThan(String color, Byte cottonPart);

    @Query("SELECT s from Socks s where s.color = ?1 and s.cottonPart > ?2")
    List<Socks> findByColorAndCottonPartMoreThan(String color, Byte cottonPart);

    @Query("SELECT s from Socks s where s.color = ?1 and s.cottonPart = ?2")
    List<Socks> findByColorAndCottonPartEqual(String color, Byte cottonPart);
}
