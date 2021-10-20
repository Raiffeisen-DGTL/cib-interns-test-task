package org.RF.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.RF.POJO.Socks;

import java.util.List;
import java.util.Optional;

public interface SocksRepos extends CrudRepository<Socks, Integer> {

    Socks findByColorAndCottonPart(String color,int cottonPart);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart > ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartGreaterThan(String color, int cottonPart);
    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart = ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartEquals(String color, int cottonPart);
    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart < ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartLessThan(String color, int cottonPart);


}
