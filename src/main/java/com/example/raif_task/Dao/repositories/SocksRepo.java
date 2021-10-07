package com.example.raif_task.Dao.repositories;

import com.example.raif_task.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksRepo extends JpaRepository<Socks,Integer> {
    List<Socks> getAllByColorIsAndCottonPartIs(String color, double cottonPart);

    List<Socks> getAllByColorIs(String color);
}
