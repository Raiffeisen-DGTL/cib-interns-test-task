package com.rf.accountingforsocks.repository;

import com.rf.accountingforsocks.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ColorRepository extends JpaRepository<Color, UUID> {
    Optional<Color> findColorByName(String name);
}
