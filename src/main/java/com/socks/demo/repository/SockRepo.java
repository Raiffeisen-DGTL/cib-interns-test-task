package com.socks.demo.repository;


import com.socks.demo.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SockRepo extends JpaRepository<Sock, Long> {

    @Modifying
    @Query("update Sock s set s.quantity = s.quantity +?2 where s.color = ?1 and s.cottonPart =?3 ")
    void incomeSocks(String color, Integer quantity, Integer cottonPart);

    @Modifying
    @Query("update Sock s set s.quantity = s.quantity -?2 where s.color = ?1 and s.cottonPart =?3 ")
    void outcomeSocks(String color, Integer quantity, Integer cottonPart);

    @Query("SELECT SUM(s.quantity) from Sock s where s.color =?1 and s.cottonPart > ?2")
    Integer calcOperationMore(String color, Integer cottonPart);

    @Query("SELECT SUM(s.quantity) from Sock s where s.color =?1 and s.cottonPart < ?2")
    Integer calcOperationLess(String color, Integer cottonPart);

    @Query("SELECT SUM(s.quantity) from Sock s where s.color =?1 and s.cottonPart = ?2")
    Integer calcOperationEquals(String color, Integer cottonPart);

    Sock findSockByColorAndCottonPart(String color, Integer cottonPart);

}
