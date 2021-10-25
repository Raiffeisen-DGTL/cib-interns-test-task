package ru.backend.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.shop.entities.Color;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findColorByColorName(String colorName);
}