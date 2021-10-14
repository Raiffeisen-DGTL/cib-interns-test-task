package com.home.sock.repository;

import com.home.sock.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findOneByColor(String color);
}
