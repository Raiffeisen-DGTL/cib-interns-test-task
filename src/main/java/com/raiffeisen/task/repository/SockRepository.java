package com.raiffeisen.task.repository;

import com.raiffeisen.task.domain.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Integer> {

    Optional<Sock> findSockByColorAndCottonPart(String color, Integer cottonPart);

    @Query(
        "select sum(u.quantity) from socks u where u.color = ?1 and u.cottonPart > ?2"
    )
    Integer getTotalQuantityMore(String color, Integer cottonPart);

    @Query(
            "select sum(u.quantity) from socks u where u.color = ?1 and u.cottonPart = ?2"
    )
    Integer getTotalQuantityEquals(String color, Integer cottonPart);

    @Query(
            "select sum(u.quantity) from socks u where u.color = ?1 and u.cottonPart < ?2"
    )
    Integer getTotalQuantityLess(String color, Integer cottonPart);
}
