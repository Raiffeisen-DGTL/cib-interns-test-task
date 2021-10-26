package ru.raiffeisen.socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socks.entity.Color;

import java.util.Optional;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
    Optional<Color> findByName(String name);
}
