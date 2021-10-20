package com.raif.app.dao.mapper;

import com.raif.app.dao.model.SocksStorage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SockStorageMapper implements RowMapper<SocksStorage> {
    @Override
    public SocksStorage mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new SocksStorage(
                rs.getLong("id"),
                rs.getString("color"),
                rs.getLong("quantity"),
                rs.getInt("cotton_part")
        );
    }
}
