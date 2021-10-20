package net.raiffaisen.interntest.repository;

import net.raiffaisen.interntest.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {
    Sock findByCottonPartAndColor(int cottonPart,String color);
    List<Sock> findByColorAndCottonPart(String color, int cottonPart);
    List<Sock> findByCottonPartLessThanAndColor(int cottonPart,String color);
    List<Sock> findByCottonPartGreaterThanAndColor(int cottonPart,String color);
}
