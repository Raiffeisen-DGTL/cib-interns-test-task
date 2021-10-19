package net.raiffaisen.interntest.repository;

import net.raiffaisen.interntest.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findByCottonPartAndColor(int cottonPart,String color);
    List<Sock> findByCottonPartAndColorLessThan(int cottonPart,String color);
    List<Sock> findByCottonPartAndColorGreaterThan(int cottonPart,String color);
}
