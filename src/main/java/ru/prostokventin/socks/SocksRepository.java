package ru.prostokventin.socks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends CrudRepository<Socks, SocksId> {

    @Query("select coalesce(sum(quantity),0) from Socks where color = ?1 and cottonPart > ?2")
    int totalQuantityByColorCottonPartGreaterThan(String color, int cottonPart);
    @Query("select coalesce(sum(quantity),0) from Socks where color = ?1 and cottonPart < ?2")
    int totalQuantityByColorCottonPartLessThan(String color, int cottonPart);
    @Query("select coalesce(sum(quantity),0) from Socks where color = ?1 and cottonPart = ?2")
    int totalQuantityByColorCottonPartEquals(String color, int cottonPart);
}
