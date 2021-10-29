package com.example.socksspring.repositories;

import com.example.socksspring.Socks;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocksRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SocksRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Socks> getSocksLessThan(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = :color and cotton_part < :cottonPart",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cottonPart", cottonPart),
                new SockMapper()
        );
    }

    public List<Socks> getSocksEquals(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = :color and cotton_part = :cottonPart",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cottonPart", cottonPart),
                new SockMapper()
        );
    }

    public List<Socks> getSocksGreaterThan(String color, int cottonPart) {
        return jdbcTemplate.query("select * from socks where color = :color and cotton_part > :cottonPart",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cottonPart", cottonPart),
                new SockMapper()
        );
    }


    public List<Socks> upsertAddSocks(Socks socks) {
        return jdbcTemplate.query("\n" +
                        "insert into socks (color, cotton_part, quantity)\n" +
                        "values (:color, :cottonPart, :quantity) on conflict (color, cotton_part) do\n" +
                        "update\n" +
                        "set color = :color, cotton_part = :cottonPart, quantity = socks.quantity + :quantity returning *",
                new MapSqlParameterSource()
                        .addValue("color", socks.getColor())
                        .addValue("cottonPart", socks.getCottonPart())
                        .addValue("quantity", socks.getQuantity()),
                new SockMapper()
        );
    }


    public List<Socks> updateRemoveSocks(Socks socks) {
        return jdbcTemplate.query("update socks \n" +
                        "set color = :color, cotton_part = :cottonPart, quantity = greatest( socks.quantity - :quantity , 0) " +
                        "where color = :color and cotton_part = :cottonPart returning *",
                new MapSqlParameterSource()
                        .addValue("color", socks.getColor())
                        .addValue("cottonPart", socks.getCottonPart())
                        .addValue("quantity", socks.getQuantity()),
                new SockMapper());
    }
}

