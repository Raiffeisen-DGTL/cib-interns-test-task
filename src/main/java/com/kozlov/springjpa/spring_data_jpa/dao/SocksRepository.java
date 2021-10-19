package com.kozlov.springjpa.spring_data_jpa.dao;


import com.kozlov.springjpa.spring_data_jpa.entity.Socks;
import com.kozlov.springjpa.spring_data_jpa.entity.SocksId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, SocksId> {

    List<Socks> findSocksByColorAndCottonPartIsGreaterThan(String color, int cottonPart);

    List<Socks> findSocksByColorAndCottonPartIsLessThan(String color, int cottonPart);

    List<Socks> findSocksByColorAndCottonPartIs(String color, int cottonPart);

}
