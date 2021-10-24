package model.repositories;

import model.models.Sock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SockRepository extends CrudRepository<Sock, Long> {

    List<Sock> findByColorAndCottonPartGreaterThan(String color, int cottonPart);

    List<Sock> findByColorAndCottonPartLessThan(String color, int cottonPart);

    Optional<Sock> findByColorAndCottonPartEquals(String color, int cottonPart);


}
