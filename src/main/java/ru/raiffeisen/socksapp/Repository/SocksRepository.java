package ru.raiffeisen.socksapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raiffeisen.socksapp.Model.Socks;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    Socks findByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<Socks> findByColorAndCottonPartLessThan(String color, int cottonPart);
}
