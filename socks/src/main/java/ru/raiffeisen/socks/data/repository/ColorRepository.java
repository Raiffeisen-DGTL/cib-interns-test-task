package ru.raiffeisen.socks.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socks.data.entity.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
}
