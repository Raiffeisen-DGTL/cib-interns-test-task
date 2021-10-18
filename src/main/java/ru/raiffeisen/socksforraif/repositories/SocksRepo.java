package ru.raiffeisen.socksforraif.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socksforraif.models.Socks;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepo extends JpaRepository<Socks, String> {
    List<Socks> findAllByColorAndCottonPart(String color, Byte cottonPart);
    List<Socks> findAllByColorAndCottonPartAfter(String color, Byte cottonPart);
    List<Socks> findAllByColorAndCottonPartBefore(String color, Byte cottonPart);
    Optional<Socks> findByColorAndCottonPart(String color, Byte cottonPart);
}
