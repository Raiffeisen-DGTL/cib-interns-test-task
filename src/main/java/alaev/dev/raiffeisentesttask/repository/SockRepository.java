package alaev.dev.raiffeisentesttask.repository;

import alaev.dev.raiffeisentesttask.domain.Sock;
import org.springframework.data.repository.CrudRepository;

public interface SockRepository extends CrudRepository<Sock, Long> {

  boolean existsByColorAndCottonPart(String color, Integer cottonPart);

  Sock getSockByColorAndCottonPart(String color, Integer cottonPart);
}
