package ru.mytasks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import ru.mytasks.models.Sock;

public class SocksMapper implements RowMapper<Sock> {
	private ColorsDAO colorsDAO;
	
	@Autowired
	public SocksMapper(ColorsDAO colorsDAO) {
		this.colorsDAO = colorsDAO;
	}

	@Override
	public Sock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sock sock = new Sock();
			
		sock.setColor(colorsDAO.get(rs.getInt("color")).getName());
		sock.setCottonPart(rs.getInt("cotton_part"));
		sock.setQuantity(rs.getInt("quantity"));
		
		return sock;
	}

}
