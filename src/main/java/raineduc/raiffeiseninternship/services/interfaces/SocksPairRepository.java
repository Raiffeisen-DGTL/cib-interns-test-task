package raineduc.raiffeiseninternship.services.interfaces;

import org.springframework.data.repository.CrudRepository;
import raineduc.raiffeiseninternship.entities.Socks;

import java.util.List;

public interface SocksPairRepository extends CrudRepository<Socks, Long> {
    Socks findSocksByColorAndCottonPart(String color, byte cottonPart);
    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    List<Socks> findAllByColorAndCottonPartEquals(String color, byte cottonPart);
    List<Socks> findAllByColorAndCottonPartLessThan(String color, byte cottonPart);
}
