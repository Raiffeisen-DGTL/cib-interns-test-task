package cib.test.cibtest.repository;

import cib.test.cibtest.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Repository extends JpaRepository<Sock, Long> {
    List<Sock> getSocksByColorAndCottonPartLessThan(String color, Long cottonPart); // поиск по базе меньше чем
    Optional<Sock> getSocksByColorAndCottonPartEquals(String color, Long cottonPart); // равно
    List<Sock> getSocksByColorAndCottonPartIsGreaterThan(String color, Long cottonPart); // больше чем

}
