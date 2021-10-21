package com.example.demo.repository;

import com.example.demo.domain.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> getSocksByColorAndCottonPartLessThan(String color, Long cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartEquals(String color, Long cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);
}
