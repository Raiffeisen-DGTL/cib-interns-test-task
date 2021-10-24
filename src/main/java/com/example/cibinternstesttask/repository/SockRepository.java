package com.example.cibinternstesttask.repository;

import com.example.cibinternstesttask.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findAllByColor(String color);

    Sock findByColorAndCottonPart(String color, Long cottonPart);

    List<Sock> findAllByColorAndCottonPartLessThan(String color, Long cottonPart);

    List<Sock> findAllByColorAndCottonPartEquals(String color, Long cottonPart);

    List<Sock> findAllByColorAndCottonPartGreaterThan(String color, Long cottonPart);
}
