package com.example.socks.repository;

import com.example.socks.entity.Color;
import com.example.socks.entity.Sock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SockRepository extends CrudRepository<Sock, Long> {
    Optional<Sock> findByColorAndCottonPart(Color color, int cottonPart);
    Optional<Sock> findByColorNameAndCottonPart(String colorName, int cottonPart);
    List<Sock> findByColorAndCottonPartLessThan(Color color, int cottonPart);
    List<Sock> findByColorAndCottonPartGreaterThan(Color color, int cottonPart);
}
