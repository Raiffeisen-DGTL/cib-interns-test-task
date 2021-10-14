package com.test_task.socks_for_test_task.repository;

import com.test_task.socks_for_test_task.model.Socks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends CrudRepository<Socks, Long> {
    Optional<Socks> findByColorAndCottonPart(String color, int cottonPart);
    Optional<Socks> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
    Optional<Socks> findByColorAndCottonPartLessThan(String color, int cottonPart);
    Optional<Socks> findByColorAndCottonPartEquals(String color, int cottonPart);
}
