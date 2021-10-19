package com.java.task.repository;

import com.java.task.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findByColor(String color);
}
