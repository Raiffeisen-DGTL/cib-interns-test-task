package net.raiffaisen.interntest.repository;

import net.raiffaisen.interntest.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findByCottonPart(int cottonPart);
    List<Sock> findByColor(String color);
}
