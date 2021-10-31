package com.example.socksspring.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.socksspring.Socks;
import org.springframework.jdbc.core.RowMapper;

public class SockMapper implements RowMapper<Socks> {
    @Override
    public Socks mapRow(ResultSet resultSet, int i) throws SQLException {
       return new Socks(
                resultSet.getLong("id"),
                resultSet.getString("color"),
                resultSet.getInt("cotton_part"),
                resultSet.getInt("quantity"));

    }
}