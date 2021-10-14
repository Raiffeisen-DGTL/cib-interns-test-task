package com.example.company.dao;

import com.example.company.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface SockRepository extends JpaRepository<Sock, Integer> {
    Optional<Sock> getSockByColorAndCottonPartLessThan(String color, int cottonPart);
    Optional<Sock> getSockByColorAndCottonPartGreaterThan(String color, int cottonPart);
    Optional<Sock> getSockByColorAndCottonPartEquals(String color, int cottonPart);
}
