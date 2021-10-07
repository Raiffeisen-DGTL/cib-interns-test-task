package raineduc.raiffeiseninternship.services.interfaces;

import org.springframework.data.repository.CrudRepository;
import raineduc.raiffeiseninternship.entities.SocksPair;

import java.util.List;

public interface SocksPairRepository extends CrudRepository<SocksPair, Long> {
    List<SocksPair> findAllByColorAndCottonPart(String color, byte cottonPart);
    int countByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    int countByColorAndCottonPartEquals(String color, byte cottonPart);
    int countByColorAndCottonPartLessThan(String color, byte cottonPart);
}
