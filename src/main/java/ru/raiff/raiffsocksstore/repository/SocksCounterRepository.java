package ru.raiff.raiffsocksstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.raiff.raiffsocksstore.entity.SocksCounter;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocksCounterRepository extends JpaRepository<SocksCounter, UUID> {

    @Query(
            value = "" +
                    "SELECT sc.* FROM socks_counter sc " +
                    "JOIN socks_category scat on sc.category_id = scat.id " +
                    "WHERE scat.color = ?1 AND scat.cotton_part = ?2"
            , nativeQuery = true)
    Optional<SocksCounter> findByColorAndCottonPartWithCounter(String color, Short cottonPart);

    @Query(
            value = "" +
                    "SELECT sum(sc.quantity) FROM socks_counter sc " +
                    "JOIN socks_category scat on sc.category_id = scat.id " +
                    "WHERE scat.color = ?1 AND scat.cotton_part > ?2"
            , nativeQuery = true)
    Long sumByColorAndCottonPartMoreThan(String color, Short cottonPart);

    @Query(
            value = "" +
                    "SELECT sum(sc.quantity) FROM socks_counter sc " +
                    "JOIN socks_category scat on sc.category_id = scat.id " +
                    "WHERE scat.color = ?1 AND scat.cotton_part < ?2"
            , nativeQuery = true)
    Long sumByColorAndCottonPartLessThan(String color, Short cottonPart);

    @Query(
            value = "" +
                    "SELECT sum(sc.quantity) FROM socks_counter sc " +
                    "JOIN socks_category scat on sc.category_id = scat.id " +
                    "WHERE scat.color = ?1 AND scat.cotton_part = ?2"
            , nativeQuery = true)
    Long sumByColorAndCottonPartEquals(String color, Short cottonPart);
}
