package ru.hlem.storesocksraif.repository;

import ru.hlem.storesocksraif.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long>{
    List<Sock> findAllByColorAndCottonPartIsLessThan(String color, int cottonPart);
    List<Sock> findByColorAndCottonPart(String color, int cottonPart);
    List<Sock> findAllByColorAndCottonPartIsGreaterThan(String color, int cottonPart);
}
