package joha.ch.raif.repo;

import joha.ch.raif.domain.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock,Long> {
     List<Sock> findByColor(String color);
     List<Sock> findByCottonPart(String cottonPart);
     Sock findByColorAndCottonPart(String color,String cottonPart);
}
