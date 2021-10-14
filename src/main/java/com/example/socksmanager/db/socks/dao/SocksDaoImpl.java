package com.example.socksmanager.db.socks.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksDaoImpl implements SocksDao {
    private final String INSERT_SOCKS_QUERY = "insert into socks (color, cotton_part, quantity) values (:color, :cotton_part, :quantity)";
    private final String INCOME_SOCKS_QUERY = "update socks set quantity = quantity + :quantity where color = :color and cotton_part = :cotton_part";
    private final String OUTCOME_SOCKS_QUERY = "update socks set quantity = quantity - :quantity where color = :color and cotton_part = :cotton_part";
    private final String GET_MORE_QUERY = "select sum(quantity) counts from socks  where color = :color and cotton_part > :cotton_part ";
    private final String GET_EQUAL_QUERY = "select sum(quantity) counts from socks  where color = :color and cotton_part = :cotton_part ";
    private final String GET_LESS_QUERY = "select sum(quantity) counts from socks  where color = :color and cotton_part < :cotton_part ";

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public SocksDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public void insertSocks(String color, int cottonPart, int quantity) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        paramMap.put("quantity", quantity);
        namedJdbcTemplate.update(INSERT_SOCKS_QUERY, paramMap);
    }

    @Override
    public void incomeSocks(String color, int cottonPart, int quantity) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        paramMap.put("quantity", quantity);
        namedJdbcTemplate.update(INCOME_SOCKS_QUERY, paramMap);
    }

    @Override
    public void outcomeSocks(String color, int cottonPart, int quantity) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        paramMap.put("quantity", quantity);
        namedJdbcTemplate.update(OUTCOME_SOCKS_QUERY, paramMap);
    }

    @Override
    public int getMoreCount(String color, int cottonPart) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        return namedJdbcTemplate.queryForObject(GET_MORE_QUERY, paramMap, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("counts");
            }
        });
    }

    @Override
    public int getEqualCount(String color, int cottonPart) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        return namedJdbcTemplate.queryForObject(GET_EQUAL_QUERY, paramMap, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("counts");
            }
        });
    }

    @Override
    public int getLessCount(String color, int cottonPart) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("color", color);
        paramMap.put("cotton_part", cottonPart);
        return namedJdbcTemplate.queryForObject(GET_LESS_QUERY, paramMap, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("counts");
            }
        });
    }
}
