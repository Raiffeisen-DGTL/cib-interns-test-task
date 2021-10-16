package com.raifaisentask.dao;

import com.raifaisentask.data.BadRequestException;
import com.raifaisentask.data.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbsSocksDao implements SocksDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getSocksQuantityByColor(String color) {
        String SQL_SELECT_SOCKS_QUANTITY_BY_COLOR = "SELECT sum(quantity) FROM Socks where color=?";

        return jdbcTemplate.queryForObject(SQL_SELECT_SOCKS_QUANTITY_BY_COLOR,
                (rs, rowNum) -> {
                    return (Long) rs.getLong(1);
                },
                color.toLowerCase());
    }

    @Override
    public Long getSocksQuantityByCottonPart(String operation, int cottonPart) throws BadRequestException {
        if (cottonPart < 0 || cottonPart > 100)
            throw new BadRequestException("параметр cottonPart должен быть в пределах [0,100]");

        String SQL_SELECT_SOCKS_QUANTITY_BY_COTTON_PART = "SELECT sum(quantity) FROM Socks where cotton_Part" + operation + " ? ";

        return jdbcTemplate.queryForObject(SQL_SELECT_SOCKS_QUANTITY_BY_COTTON_PART,
                (rs, rowNum) -> {
                    return (Long) rs.getLong(1);
                },
                cottonPart);
    }

    @Override
    public Long getSocksQuantityByColorAndCottonPart(String color, String operation, int cottonPart) throws BadRequestException {
        if (cottonPart < 0 || cottonPart > 100)
            throw new BadRequestException("параметр cottonPart должен быть в пределах [0,100]");

        String SQL_SELECT_SOCKS_QUANTITY_BY_COLOR_AND_COTTON_PART =
                "SELECT sum(quantity) FROM Socks where color=? and cotton_Part " + operation + " ? ";
        return jdbcTemplate.queryForObject(SQL_SELECT_SOCKS_QUANTITY_BY_COLOR_AND_COTTON_PART,
                (rs, rowNum) -> {
                    return rs.getLong(1);
                },
                color.toLowerCase(),
                cottonPart);
    }

    @Override
    public void addSocks(String color, int cottonPart, Long quantity) throws BadRequestException {
        if ((cottonPart < 0 || cottonPart > 100) || quantity < 0) {
            throw new BadRequestException("параметр cottonPart должен быть в пределах [0,100]," +
                    "а параметр quantity должен быть неотрицательным целым числом");
        }
        Socks socks = getRowByColorAndCottonPart(color, cottonPart);
        // Если не существует записи, добавляем её
        if (socks == null) {
            String SQL_INSERT_ROW =
                    "INSERT INTO socks (color, cotton_part, quantity) values (?,?,?)";
            jdbcTemplate.update(SQL_INSERT_ROW,
                    color.toLowerCase(),
                    cottonPart,
                    quantity);
            // Если существует - обновляем её
        } else {
            String SQL_UPDATE_ROW = "UPDATE socks SET quantity = ? where id=?";
            jdbcTemplate.update(SQL_UPDATE_ROW,
                    quantity + socks.getQuantity(),
                    socks.getId());
        }
    }

    @Override
    public void removeSocks(String color, int cottonPart, Long quantity) throws BadRequestException {
        if ((cottonPart < 0 || cottonPart > 100) || quantity < 0) {
            throw new BadRequestException("параметр cottonPart должен быть в пределах [0,100]," +
                    " а параметр quantity должен быть неотрицательным целым числом");
        }
        Socks socks = getRowByColorAndCottonPart(color, cottonPart);
        // Если не существует записи, то возвращаем BAD_REQUEST, т.к. вычитать не из чего
        if (socks == null) {
            throw new BadRequestException("На складе не существуют носки с данными параметрами");
        } else {
            Long dif = socks.getQuantity() - quantity;
            // Не разрешаем взять больше носков на складе, чем есть
            if (dif < 0)
                throw new BadRequestException("На складе только "+socks.getQuantity()+" пар(-а) носков");

            String SQL_UPDATE_ROW = "UPDATE socks SET quantity = ? where id=?";
            jdbcTemplate.update(SQL_UPDATE_ROW,
                    dif,
                    socks.getId());
        }
    }

    private Socks getRowByColorAndCottonPart(String color, int cottonPart) {
        String SQL_SELECT_ROW = "SELECT * FROM SOCKS WHERE color=? and cotton_part=?";
        Socks s;
        try {
            s = jdbcTemplate.queryForObject(SQL_SELECT_ROW, ((rs, rowNum) -> {
                        Socks socks = new Socks();

                        socks.setId(rs.getLong(1));
                        socks.setColor(rs.getString(2));
                        socks.setCottonPart(rs.getInt(3));
                        socks.setQuantity(rs.getLong(4));
                        return socks;
                    }),
                    color.toLowerCase(),
                    cottonPart);
        } catch (DataAccessException e){
            return null;
        }
        return s;
    }
}
