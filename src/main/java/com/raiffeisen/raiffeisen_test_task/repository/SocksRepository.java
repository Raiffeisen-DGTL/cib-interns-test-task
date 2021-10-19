package com.raiffeisen.raiffeisen_test_task.repository;

import com.raiffeisen.raiffeisen_test_task.model.Socks;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocksRepository extends CrudRepository<Socks, Long> {
    Optional<Socks> getSocksByColorAndCottonPartLessThan(String color, byte cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartEquals(String color, byte cottonPart);

    Optional<Socks> getSocksByColorAndCottonPartGreaterThan(String color, byte cottonPart);
}
