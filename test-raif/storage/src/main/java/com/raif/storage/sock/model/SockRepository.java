package com.raif.storage.sock.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findByColorAndCottonPart(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartLessThan(String color, int cottonPart);
}