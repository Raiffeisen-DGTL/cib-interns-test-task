package com.example.demo.sock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {
    //@Query("SELECT s.quantity FROM Sock s WHERE s.color = ?1 AND s.cotton_part > ?2")
    Optional<Sock> findByColorAndCottonPartGreaterThan(String color, Byte cottonPart);
    Optional<Sock> findByColorAndCottonPartLessThan(String color, Byte cottonPart);
    Optional<Sock> findByColorAndCottonPart(String color, Byte cottonPart);

    @Transactional
    @Modifying
    @Query("UPDATE Sock s SET s.quantity = :quantity WHERE s.id = :id")
    int setQuantity(@Param("quantity") Short quantity, @Param("id") Long id);

}
