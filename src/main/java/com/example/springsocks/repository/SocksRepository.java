package com.example.springsocks.repository;

import com.example.springsocks.domain.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * SocksRepository.
 *
 * @author Alexander_Tupchin
 */
public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query("SELECT s.quantity FROM Socks s " +
            "WHERE s.color = :color " +
            "and s.cottonPart > :cottonPart ")
    Optional<Long> findCountSockWithParamMoreThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query("SELECT s.quantity FROM Socks s " +
            "WHERE s.color = :color " +
            "and s.cottonPart < :cottonPart ")
    Optional<Long> findCountSockWithParamLessThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query("SELECT s.quantity FROM Socks s " +
            "WHERE s.color = :color " +
            "and s.cottonPart = :cottonPart ")
    Optional<Long> findCountSockWithParamEquals(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    Optional<Socks> findByColorAndCottonPart(@Param("color") String color,
                                   @Param("cottonPart") Integer cottonPart);
}
