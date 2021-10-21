package ru.mirraim.cib_interns_test_task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirraim.cib_interns_test_task.entity.Sock;

import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Integer> {
    Sock findFirstByColorAndCottonPart(String color, int cottonPart);
    List<Sock> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<Sock> findByColorAndCottonPartLessThan(String color, int cottonPart);
    List<Sock> findByColorAndCottonPart(String color, int cottonPart);

}
