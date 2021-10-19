package com.raiffeisen.interntask.repos;

import com.raiffeisen.interntask.classes.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColorRepository extends JpaRepository<Color, UUID> {
    Color getColorByName(String name);
    boolean existsByName(String name);
}
