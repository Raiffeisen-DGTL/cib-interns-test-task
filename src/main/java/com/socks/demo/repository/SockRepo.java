package com.socks.demo.repository;


import com.socks.demo.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SockRepo extends JpaRepository<Sock, Long> {

    @Modifying
    @Query("update Sock  s set s.quantity = s.quantity +?2 where s.color = ?1 and s.cottonPart =?3 ")
    void incomeSocks(String color, Integer quantity, Integer cottonPart);

    @Modifying
    @Query("update Sock  s set s.quantity = s.quantity -?2 where s.color = ?1 and s.cottonPart =?3 ")
    void outcomeSocks(String color, Integer quantity, Integer cottonPart);

    @Modifying
    @Query("from Sock s where s.color =?1 and s.cottonPart > ?2")
    List<Sock> calcOperationMore(String color, Integer cottonPart);

    @Modifying
    @Query("from Sock s where s.color =?1 and s.cottonPart < ?2")
    List<Sock> calcOperationLess(String color, Integer cottonPart);

    @Modifying
    @Query("from Sock s where s.color =?1 and s.cottonPart = ?2")
    List<Sock> calcOperationEquals(String color, Integer cottonPart);

    Sock findSockByColorAndCottonPart(String color, Integer cottonPart);

}
