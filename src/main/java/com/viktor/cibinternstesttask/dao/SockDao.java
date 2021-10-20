package com.viktor.cibinternstesttask.dao;

import com.viktor.cibinternstesttask.entity.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SockDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateIncome(Sock sock) {
        jdbcTemplate.update("insert into socks (color, cotton_part, quantity) " +
                        "values (?, ?, ?) on conflict (color, cotton_part) do update set quantity = socks.quantity + ?",
                sock.getColor(), sock.getCottonPart(), sock.getQuantity(), sock.getQuantity());
    }

    public void updateOutcome(Sock sock) {
        jdbcTemplate.update("update socks set quantity = socks.quantity - ? " +
                        "where color = ? and cotton_part = ?",
                sock.getQuantity(), sock.getColor(), sock.getCottonPart());
    }

    public Long countSockWithMoreThanCottonPart(String color, Integer cottonPart) {
        return jdbcTemplate.queryForObject("select sum(quantity) from socks " +
                        "where color = ? and cotton_part > ?",
                Long.class,
                color, cottonPart);
    }

    public Long countSockWithLessThanCottonPart(String color, Integer cottonPart) {
        return jdbcTemplate.queryForObject("select sum(quantity) from socks " +
                        "where color = ? and cotton_part < ?",
                Long.class,
                color, cottonPart);
    }

    public Long countSockWithEqualCottonPart(String color, Integer cottonPart) {
        return jdbcTemplate.queryForObject("select sum(quantity) from socks " +
                        "where color = ? and cotton_part = ?",
                Long.class,
                color, cottonPart);
    }
}
