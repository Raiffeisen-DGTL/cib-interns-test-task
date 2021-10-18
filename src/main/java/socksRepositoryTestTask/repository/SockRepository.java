package socksRepositoryTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socksRepositoryTestTask.model.Color;
import socksRepositoryTestTask.model.Sock;

import java.util.List;
import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findByColorAndCottonPart(Color color, int cottonPart);

    List<Sock> findByColorAndCottonPartLessThan(Color color, int cottonPart);

    List<Sock> findByColorAndCottonPartGreaterThan(Color color, int cottonPart);
}
