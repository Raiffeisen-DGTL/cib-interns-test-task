package ru.khromov.raiftask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.khromov.raiftask.DAO.Sock;

import java.util.List;
import java.util.Optional;

@Component
public interface SocksRepository extends CrudRepository<Sock, Long> {

    Sock save(Sock socks);

    List<Sock> findAllBy();

    List<Sock> findByColor(String color);

    Optional<Sock> findByColorAndCottonPartEquals(String color, int cottonPart);
}
