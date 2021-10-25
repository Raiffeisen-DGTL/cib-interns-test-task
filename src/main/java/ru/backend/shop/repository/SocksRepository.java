package ru.backend.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.shop.entities.Color;
import ru.backend.shop.entities.Socks;
import java.util.List;
import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Long> {

    Optional<Socks> findByColorAndCottonPart(Color color, Integer cottonPart);

    List<Socks> findSocksByColorAndCottonPart(Color color, Integer cottonPart);

    List<Socks> findSocksByColorAndCottonPartGreaterThan(Color color, Integer cottonPart);

    List<Socks> findSocksByColorAndCottonPartLessThan(Color color, Integer cottonPart);
}