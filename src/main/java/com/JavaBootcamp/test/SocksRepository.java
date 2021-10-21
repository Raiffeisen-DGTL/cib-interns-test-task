package com.JavaBootcamp.test;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    List<Socks> findByCottonPartAndColor(byte cottonPart, String color);
}