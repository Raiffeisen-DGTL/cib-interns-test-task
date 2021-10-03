package alaev.dev.raiffeisentesttask.repository;

import alaev.dev.raiffeisentesttask.domain.Sock;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SockRepository extends CrudRepository<Sock, Long> {

  Optional<Sock> findSockByColorAndCottonPart(String color, Integer cottonPart);

  Optional<Sock> findSockByColorAndCottonPartAndQuantityIsLessThanEqual(String color,
                                                                        Integer cottonPart,
                                                                        Integer quantity);
}
