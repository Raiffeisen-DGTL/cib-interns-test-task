package tz.example.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tz.example.project.models.Socks;

import java.util.Optional;

@Component
public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> findByColorAndCottonPart(String color, Integer cottonPart);
    Optional<Socks> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
    Optional<Socks> findByColorAndCottonPartLessThan(String color, Integer cottonPart);
    Optional<Socks> findByColorAndCottonPartEquals(String color, Integer cottonPart);
}