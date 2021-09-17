package com.example.sock.repos;

import com.example.sock.domain.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> getSocksByColorAndCottonPartLessThan(String color, Long cottonPart);
    Optional<Socks> getSocksByColorAndCottonPartEquals(String color, Long cottonPart);
    Optional<Socks> getSocksByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);
}
