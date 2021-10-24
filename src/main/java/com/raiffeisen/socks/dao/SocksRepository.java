package com.raiffeisen.socks.dao;

import com.raiffeisen.socks.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends JpaRepository<Sock, Long> {
    Optional<Sock> getSockByColorAndCottonPart(String color, Integer cottonPart);

    List<Sock> findByColorAndCottonPartEquals(String color, Integer cottonPart);

    List<Sock> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

    List<Sock> findByColorAndCottonPartLessThan(String color, Integer cottonPart);

}
