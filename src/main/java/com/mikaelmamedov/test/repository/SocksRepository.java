package com.mikaelmamedov.test.repository;

import com.mikaelmamedov.test.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> findByColorAndCottonPart(String color, int cottonPart);

    Optional<List<Socks>> findAllByColorAndCottonPartEquals(String color, int quantity);
    Optional<List<Socks>> findAllByColorAndCottonPartIsGreaterThan(String color, int quantity);
    Optional<List<Socks>> findAllByColorAndCottonPartIsLessThan(String color, int quantity);
}
