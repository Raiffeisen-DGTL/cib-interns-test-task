package com.example.socksstr.Repository;

import com.example.socksstr.Model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query("select s from Socks s where s.color = ?1 and s.cottonPart = ?2")
    List<Socks> findSocksByColorAndCottonPart (String color, Long cottonPart);

    @Query("select  s from Socks s where s.color =?1")
    List<Socks> findSocksByColor (String color);

}
