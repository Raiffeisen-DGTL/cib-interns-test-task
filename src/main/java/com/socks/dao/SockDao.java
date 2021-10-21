package com.socks.dao;

import com.socks.models.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Locale;

@Component
public class SockDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertOrUpdate(Sock sock){
        if(isExist(sock)){
            update(sock);
        } else {
            add(sock);
        }
    }

    public void update(Sock sock){
        jdbcTemplate.update("update tb_socks set cotton_part = ?, quantity = quantity + ? where color = ?",
                sock.getCottonPart(), sock.getQuantity(), sock.getColor().toLowerCase());
    }

    public void add(Sock sock){
        jdbcTemplate.update("insert into tb_socks values(?, ?, ?)",
                sock.getColor().toLowerCase(), sock.getCottonPart(), sock.getQuantity());
    }

    public boolean isExist(Sock sock){
        String sql = "select * from tb_socks where color = ? and cotton_part = ?";
        boolean hasRecord = Boolean.TRUE.equals(jdbcTemplate.query(sql, new Object[]{sock.getColor().toLowerCase(), sock.getCottonPart()},
                (ResultSet resultSet) -> {
                    if (resultSet.next()) {
                        return true;
                    }
                    return false;
                }));
        return hasRecord;
    }

    public void reduce(Sock sock){
        if (isExist(sock)){
        jdbcTemplate.update("update tb_socks set cotton_part = ?, quantity = quantity - ? where color = ?",
                sock.getCottonPart(), sock.getQuantity(), sock.getColor().toLowerCase());
        }
    }

    public int getCountOfSocks(String color, String operation, int cottonPart){
        int result = 0;
        switch (operation){
            case ("moreThan"):
                String moreThenSql = "select ifnull((select sum(quantity) from tb_socks where color = ? and cotton_part > ?), 0)";
                result = jdbcTemplate.queryForObject(moreThenSql, new Object[]{color.toLowerCase(), cottonPart}, Integer.class);
                break;
            case ("lessThan"):
                String lessThanSql = "select ifnull((select sum(quantity) from tb_socks where color = ? and cotton_part < ?), 0)";
                result = jdbcTemplate.queryForObject(lessThanSql, new Object[]{color.toLowerCase(), cottonPart}, Integer.class);
                break;
            case ("equal"):
                String equalSql = "select ifnull((select sum(quantity) from tb_socks where color = ? and cotton_part = ?), 0)";
                result = jdbcTemplate.queryForObject(equalSql, new Object[]{color.toLowerCase(), cottonPart}, Integer.class);
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }
}
