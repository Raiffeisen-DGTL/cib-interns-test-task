package ru.testcase.accountingSocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testcase.accountingSocks.model.Socks;
import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    List<Socks> findByCottonpart(int cottonpart);
    Socks findByColorAndCottonpart(String color, Integer cottonpart);

    List<Socks> findByColorAndCottonpartGreaterThan(String color, Integer cottonpart);
    List<Socks> findByColorAndCottonpartLessThan(String color, Integer cottonpart);
    List<Socks> findByColorAndCottonpartEquals(String color, Integer cottonpart);

}
