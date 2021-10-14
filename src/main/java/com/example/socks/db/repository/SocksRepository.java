package com.example.socks.db.repository;

import com.example.socks.db.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    List<Socks> findByCottonPartIsLessThanAndColorEquals(@Param("cotton_part") int cottonPart, String color);

    List<Socks> findByCottonPartGreaterThanAndColorEquals(@Param("cotton_part") int cottonPart, String color);

    List<Socks> findByCottonPartEqualsAndColorEquals(@Param("cotton_part") int cottonPart,String color);

    Socks findByCottonPartAndColor(@Param("cotton_part") int cottonPart, @Param("color") String color);

}

