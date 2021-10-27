package ru.raiffeisen.api.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.api.socks.entity.Socks;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {

    Socks findByColorAndCottonPart(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPartLessThan(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPart(String color, int cottonPart);
}
