package ru.raiffeisen.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raiffeisen.core.model.ColorInfoModel;

import java.util.Optional;

public interface ColorsRepository extends JpaRepository<ColorInfoModel, Long> {

    Optional<ColorInfoModel> findByColor(String color);

}
