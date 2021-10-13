package com.winogradov.task_socks.repository;

import com.winogradov.task_socks.model.Socks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocksRepository extends CrudRepository<Socks, Long> {

    Optional<Socks> findSocksByColorAndCottonPart(String color, Integer cottonPart);

    Optional<Socks> findSocksByColorAndCottonPartAndQuantityIsGreaterThanEqual(String color,
                                                                                Integer cottonPart,
                                                                                Integer quantity);

    @Query("select sum(s.quantity) "
            + "from socks s "
            + "where s.color = ?1 and s.cottonPart = ?2"
            + " group by s.color")
    Long getTotalNumberByColorAndCottonPartEqual(String color, Integer cottonPart);

    @Query("select sum(s.quantity) "
            + "from socks s "
            + "where s.color = ?1 and s.cottonPart > ?2"
            + " group by s.color")
    Long getTotalNumberByColorAndCottonPartMoreThan(String color, Integer cottonPart);

    @Query("select sum(s.quantity) "
            + "from socks s "
            + "where s.color = ?1 and s.cottonPart < ?2"
            + " group by s.color")
    Long getTotalNumberByColorAndCottonPartLessThan(String color, Integer cottonPart);
}
