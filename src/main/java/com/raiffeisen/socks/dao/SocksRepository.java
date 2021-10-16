package com.raiffeisen.socks.dao;

import com.raiffeisen.socks.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Sock, Long> {
    Optional<Sock> getSockByColorAndCottonPart(String color, Integer cottonPart);
}
