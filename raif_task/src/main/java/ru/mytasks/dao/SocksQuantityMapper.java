package ru.mytasks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SocksQuantityMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		int quantity = rs.getInt("quantity");
		return quantity;
	}

}
