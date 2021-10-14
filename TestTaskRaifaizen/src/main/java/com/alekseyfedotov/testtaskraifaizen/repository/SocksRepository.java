package com.alekseyfedotov.testtaskraifaizen.repository;

import com.alekseyfedotov.testtaskraifaizen.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Optional<Socks> findSocksByColorAndCottonPart(String color, Integer cottonPart);
    List<Socks> findSocksByColor(String color);
}
