package com.example.socksspring.repositories;

import com.example.socksspring.Socks;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocksRepository {
    private final JdbcTemplate jdbcTemplate;

    public SocksRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Socks> getSocksLessThan(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = ? and cotton_part < ?", new SockMapper(), color, cottonPart);
    }

    public List<Socks> getSocksEquals(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = ? and cotton_part = ?", new SockMapper(), color, cottonPart);
    }

    public List<Socks> getSocksGreaterThan(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = ? and cotton_part > ?", new SockMapper(), color, cottonPart);
    }


    public List<Socks> upsertAddSocks(Socks socks) {
        return jdbcTemplate.query("\n" +
                        "insert into socks (color, cotton_part, quantity)\n" +
                        "values (?, ?, ?) on conflict (color, cotton_part) do\n" +
                        "update\n" +
                        "set color = ?, cotton_part = ?, quantity = socks.quantity + ? returning *",
                new SockMapper(), socks.getColor(), socks.getCottonPart(), socks.getQuantity(), socks.getColor(), socks.getCottonPart(), socks.getQuantity());
    }


    public List<Socks> updateRemoveSocks(Socks socks) {
        return jdbcTemplate.query("update socks \n" +
                        "set color = ?, cotton_part = ?, quantity = greatest( socks.quantity - ? , 0) " +
                        "where color = ? and cotton_part = ? returning *",
                new SockMapper(), socks.getColor(), socks.getCottonPart(), socks.getQuantity(), socks.getColor(), socks.getCottonPart());
    }
}

