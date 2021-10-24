package com.fulkin.sockApp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Fulkin
 * Created on 23.10.2021
 */

public class SockRowMapper implements RowMapper<Sock> {
    @Override
    public Sock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sock(
                rs.getString("color"),
                rs.getInt("cotton_part"),
                rs.getInt("quantity")
        );
    }
}
