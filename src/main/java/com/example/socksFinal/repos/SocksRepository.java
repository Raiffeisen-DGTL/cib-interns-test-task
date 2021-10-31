package com.example.socksFinal.repos;

import com.example.socksFinal.model.SocksApp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocksRepository extends CrudRepository<SocksApp, Long> {

    int countSocksBy(String color, int cottonPart);

    @Query("SELECT count(sock) FROM SocksApp sock WHERE sock.color = :color AND sock.cottonPart > :num")
    int countSocksWithCottonMore(@Param ("color") String color, @Param ("cotton_part") int num);

    @Query("SELECT count(sock) FROM SocksApp sock WHERE sock.color = :color AND sock.cottonPart < :num")
    int countSocksWithCottonLess(@Param ("color") String color, @Param ("cotton_part") int num);
}
