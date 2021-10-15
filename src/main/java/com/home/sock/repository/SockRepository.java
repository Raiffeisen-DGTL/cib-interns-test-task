package com.home.sock.repository;

import com.home.sock.models.Color;
import com.home.sock.models.Composite;
import com.home.sock.models.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Long> {

  @Query("SELECT sum(s.quantity) FROM Sock s " +
          "INNER JOIN Color c ON s.color = c.id " +
          "INNER JOIN Composite cp ON s.composite = cp.id " +
          "WHERE s.color.color = ?1 AND s.composite.cottonPart < ?2 ")
  Integer findByColorAndCottonPartLessThan(String color, int cottonPart);
  @Query("SELECT sum(s.quantity) FROM Sock s " +
          "INNER JOIN Color c ON s.color = c.id " +
          "INNER JOIN Composite cp ON s.composite = cp.id " +
          "WHERE s.color.color = ?1 AND s.composite.cottonPart > ?2 ")
  Integer findByColorAndCottonPartGreaterThan(String color, int cottonPart);
  @Query("SELECT sum(s.quantity) FROM Sock s " +
          "INNER JOIN Color c ON s.color = c.id " +
          "INNER JOIN Composite cp ON s.composite = cp.id " +
          "WHERE s.color.color = ?1 AND s.composite.cottonPart = ?2")
  Integer findByColorAndCottonPartEquals(String color, int cottonPart);

  Optional<Sock> findOneByColorAndComposite(Color color, Composite composite);
}
