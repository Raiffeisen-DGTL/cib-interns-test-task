package ru.dnsk.accountingofsocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnsk.accountingofsocks.model.PairOfSocks;

public interface PairOfSocksRepository extends JpaRepository<PairOfSocks, Integer> {
    PairOfSocks findByColorAndCottonPart(String color, int cottonPart);

    PairOfSocks findByColorAndCottonPartLessThan(String color, int cottonPart);

    PairOfSocks findByColorAndCottonPartGreaterThan(String color, int cottonPart);
}
