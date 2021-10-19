package com.fedoseeva.socksaccountingapp.daoimpl;

import com.fedoseeva.socksaccountingapp.dao.SocksDAO;
import com.fedoseeva.socksaccountingapp.models.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SocksDAOImpl implements SocksDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SocksDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void income(Socks socks) {
        jdbcTemplate.update("INSERT INTO Socks VALUES(?, ?, ?)",
                socks.getColor(), socks.getCottonPart(), socks.getQuantity());
    }

    public void outcome(Socks socks) {
        jdbcTemplate.update("DELETE FROM Socks WHERE color=? and cottonpart=? and quantity=?",
                socks.getColor(), socks.getCottonPart(), socks.getQuantity());
    }

    public int select(String color, String operation, int cottonPart) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Socks WHERE color=? and cottonpart" + operation + "?",
                Integer.class, color, cottonPart);
    }

}
