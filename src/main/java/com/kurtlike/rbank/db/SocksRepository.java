package com.kurtlike.rbank.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, SocksKey> {
    Optional<Socks> findFirstByColorAndCottonpartGreaterThan(String color, int cottonpart);
    Optional<Socks> findFirstByColorAndCottonpartLessThan(String color, int cottonpart);
    Optional<Socks> findFirstByColorAndCottonpart(String color, int cottonpart);
}