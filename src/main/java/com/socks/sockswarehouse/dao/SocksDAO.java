package com.socks.sockswarehouse.dao;

import com.socks.sockswarehouse.models.socks.Socks;

import java.util.List;

public interface SocksDAO {
    public List<Socks> findAll();

    public Socks findById(Long id);

    public Socks findSimilar(Socks s)  throws Exception ;

    public List<Socks> findAllByColorAndCottonPartComparison(String color, String operation, String cottonPart) throws Exception;

    public int deleteById(Long id);

    public int save(Socks s);

    public int update(Socks s);
}
