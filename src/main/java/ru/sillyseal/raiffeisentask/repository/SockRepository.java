package ru.sillyseal.raiffeisentask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sillyseal.raiffeisentask.model.Sock;
import ru.sillyseal.raiffeisentask.model.SockId;

import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, SockId> {

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = ?1 AND s.cotton_part > ?2")
    Optional<Integer> countMoreThan(String color,int cotton_part);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = ?1 AND s.cotton_part = ?2")
    Optional<Integer> countEqual(String color,int cotton_part);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = ?1 AND s.cotton_part < ?2")
    Optional<Integer> countLessThan(String color, int cotton_part);
}