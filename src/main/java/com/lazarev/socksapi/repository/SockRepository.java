package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.CottonPart;
import com.lazarev.socksapi.entity.Sock;
import com.lazarev.socksapi.entity.SockColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Long> {
    Optional<Sock> findBySockColorAndCottonPart(SockColor sockColor, CottonPart cottonPart);

    @Query("select sum(s.quantity) from Sock s " +
            "inner join SockColor sc on s.sockColor.id = sc.id " +
            "inner join CottonPart cp on s.cottonPart.id = cp.id " +
            "where sc.color = :color and cp.cottonPart < :cottonPart")
    Integer sumBySockColorAndCottonPartLessThan(String color, Integer cottonPart);


    @Query("select sum(s.quantity) from Sock s " +
            "inner join SockColor sc on s.sockColor.id = sc.id " +
            "inner join CottonPart cp on s.cottonPart.id = cp.id " +
            "where sc.color = :color and cp.cottonPart > :cottonPart")
    Integer sumBySockColorAndCottonPartMoreThan(String color, Integer cottonPart);

    @Query("select sum(s.quantity) from Sock s " +
            "inner join SockColor sc on s.sockColor.id = sc.id " +
            "inner join CottonPart cp on s.cottonPart.id = cp.id " +
            "where sc.color = :color and cp.cottonPart = :cottonPart")
    Integer sumBySockColorAndCottonPartEquals(String color, Integer cottonPart);
}
