package com.example.socks.repository;

import com.example.socks.entity.Color;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
    Optional<Color> findColorByName(String name);
}
