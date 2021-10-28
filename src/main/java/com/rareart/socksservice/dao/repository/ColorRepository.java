package com.rareart.socksservice.dao.repository;

import com.rareart.socksservice.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findColorByName(String name);
}
