package ru.yakovlev.socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yakovlev.socks.model.Sock;

import java.util.List;
import java.util.Optional;


@Repository
public interface SockRepo extends CrudRepository<Sock, Long> {

    List<Sock> findByColor(String color);

    Optional<Sock> findByColorAndCottonPartEquals(String color, int cottonPart);

}