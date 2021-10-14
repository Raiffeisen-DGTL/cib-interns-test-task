package com.socks.sockswarehouse.dao;

import com.socks.sockswarehouse.controllers.SocksComparisonOperations;
import com.socks.sockswarehouse.models.socks.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocksDAOImpl implements SocksDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private SocksComparisonOperations operations;

    public SocksDAOImpl() {
        this.operations = new SocksComparisonOperations();
    }

    @Override
    public List<Socks> findAll() {
        return jdbcTemplate.query("SELECT * FROM socks", new BeanPropertyRowMapper<Socks>(Socks.class));
    }

    @Override
    public Socks findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM socks WHERE id=?",
                new BeanPropertyRowMapper<Socks>(Socks.class), id);
    }
    @Override
    public Socks findSimilar(Socks s)  throws Exception {
        String sql = String.format("SELECT * FROM `socks` WHERE `cotton_part` = %s AND `color` = (SELECT `id` FROM `colors` where `color` = '%s')", s.getCottonPart(), s.getColor());
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<Socks>(Socks.class));
    }

    @Override
    public List<Socks> findAllByColorAndCottonPartComparison(String color, String operation, String cottonPart) throws Exception {
        String operator = operations.getOperator(operation);
        String sql = String.format("SELECT * FROM `socks` WHERE `cotton_part` %s %s AND `color` = (SELECT `id` FROM `colors` where `color` = '%s')", operator, cottonPart, color);

        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Socks.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM socks WHERE id=?", id);
    }

    @Override
    public int save(Socks s) {
        String sql = String.format(
                "INSERT INTO `socks` (color, cotton_part, quantity) VALUES ((SELECT `id` FROM `colors` where `color` = '%s'), %s, %s)", s.getColor(), s.getCottonPart(), s.getQuantity());
        return jdbcTemplate.update(sql);
    }

    @Override
    public int update(Socks s) {
        String sql = String.format(
                "UPDATE `socks` SET `color` = '%s', `cotton_part` = %s, `quantity` = %s WHERE `id` = %s", s.getColor(), s.getCottonPart(), s.getQuantity(), s.getId());

        return jdbcTemplate.update(sql);
    }
}
