package com.raiffeisen.bootcamps.astakhovartem.socksapi.dao;


import com.raiffeisen.bootcamps.astakhovartem.socksapi.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksDAO extends JpaRepository<Socks, Integer> {
    public Socks findSocksByColorAndCottonPart(String color, int CottonPart);

    public List<Socks> findSocksByColor(String color);
}
