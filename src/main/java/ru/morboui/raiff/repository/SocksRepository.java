package ru.morboui.raiff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.morboui.raiff.entity.Socks;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Integer> {

    Optional<Socks> findSocksByColorAndCottonPartEquals(String color, Long cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartIsLessThan(String color, Long cottonPart);
}
