package com.fulkin.sockApp.repository;

import com.fulkin.sockApp.exception.BadRequestException;
import com.fulkin.sockApp.exception.InternalServerException;
import com.fulkin.sockApp.exception.NotFoundException;
import com.fulkin.sockApp.model.OperationSocks;
import com.fulkin.sockApp.model.Sock;
import com.fulkin.sockApp.model.SockRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fulkin
 * Created on 20.10.2021
 */

@Repository
public class SockRepository {
    private final JdbcTemplate jdbcTemplate;

    public SockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sock> getFilterListSocks(String color, OperationSocks op, int cottonPart) {
        String sql = "" +
                "SELECT *" +
                " FROM sock " +
                " WHERE color = ? AND cotton_part " + op.getSymbol() + " ?";

        return jdbcTemplate.query(sql,
                new SockRowMapper(), color, cottonPart);
    }

    public void updateSocks(Sock sock) throws InternalServerException{
        List<Sock> sockList = jdbcTemplate.query(
                "INSERT INTO sock (color, cotton_part, quantity) " +
                        "VALUES (?,?,?) ON CONFLICT (color, cotton_part) " +
                        "DO UPDATE " +
                        "SET color = ?, cotton_part = ?, quantity = sock.quantity + ? RETURNING *",
                new SockRowMapper(), sock.getColor(), sock.getCottonPart(), sock.getQuantity(), sock.getColor(), sock.getCottonPart(), sock.getQuantity()
        );
        if (sockList.isEmpty()) {
            throw new InternalServerException("Exception in update socks query");
        }
    }

    public void removeSocks(Sock sock) throws BadRequestException, NotFoundException {
        Integer a;
        try{
             a = jdbcTemplate.queryForObject("" +
                            "SELECT quantity" +
                            " FROM sock " +
                            " WHERE color = ? AND cotton_part = ?",
                    Integer.class, sock.getColor(), sock.getCottonPart()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("No information on the this parameters: color = \"%s\", cottonPart = %d", sock.getColor(), sock.getCottonPart()));
        }
        if (a == null || a < sock.getQuantity()) {
            throw new BadRequestException("There are fewer socks in stock than required");
        }
        jdbcTemplate.query(
                "UPDATE sock " +
                        "SET color = ?, cotton_part = ?, quantity = sock.quantity - ?" +
                        "WHERE color = ? AND cotton_part = ? RETURNING *",
                new SockRowMapper(), sock.getColor(), sock.getCottonPart(), sock.getQuantity(), sock.getColor(), sock.getCottonPart()
        );
    }
}
