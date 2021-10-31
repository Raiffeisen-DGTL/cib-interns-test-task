package socks_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import socks_accounting.model.Sock;
import socks_accounting.model.SockId;

/**
 * Data access layer for Sock entity
 */
@Repository
public interface SockRepository extends JpaRepository<Sock, SockId> {
    @Query(
       "SELECT coalesce(sum(quantity), 0)" +
       "FROM Sock WHERE color = ?1 and cottonPart < ?2"
    )
    int GetLessThanCottonPartQuantity(String color, int cottonPart);

    @Query(
        "SELECT coalesce(sum(quantity), 0)" +
        "FROM Sock WHERE color = ?1 and cottonPart > ?2"
    )
    int GetMoreThanCottonPartQuantity(String color, int cottonPart);

    @Query(
        "SELECT coalesce(sum(quantity), 0)" +
        "FROM Sock WHERE color = ?1 and cottonPart = ?2"
    )
    int GetWithCottonPartQuantity(String color, int cottonPart);
}
