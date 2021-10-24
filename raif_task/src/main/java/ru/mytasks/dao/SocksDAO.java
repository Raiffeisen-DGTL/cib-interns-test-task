package ru.mytasks.dao;

import ru.mytasks.models.Sock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SocksDAO {
	private ColorsDAO colorsDAO;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public SocksDAO (ColorsDAO colorsDAO, JdbcTemplate jdbcTemplate) {
		this.colorsDAO = colorsDAO;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Sock getSock(Sock sock) {
		return jdbcTemplate.query("select * from socks where color = ? and cotton_part = ?",
				new Object[] {colorsDAO.get(sock.getColor()).getId(), sock.getCottonPart()},
				new SocksMapper(colorsDAO)
				).stream().findAny().orElse(null);
	}
	
	@Transactional
	public void saveSockQuantity(Sock sock) {
		jdbcTemplate.update("update socks set quantity = ? where color = ? and cotton_part = ?",
			sock.getQuantity(),
			colorsDAO.get(sock.getColor()).getId(),
			sock.getCottonPart());
	}
	
	@Transactional
	public void addSock(Sock sock) {
		jdbcTemplate.update("insert into socks (color, cotton_part, quantity) values (?, ?, ?)",
				colorsDAO.get(sock.getColor()).getId(),
				sock.getCottonPart(),
				sock.getQuantity());
	}
	
	public int getQuantityEqual(String color, int cottonPart) {
		return (int) jdbcTemplate.query("select sum(quantity) quantity from socks where color = ? and cotton_part = ?",
				new Object[] {colorsDAO.get(color).getId(), cottonPart},
				new SocksQuantityMapper()).stream().findAny().orElse(0);
	}
	
	public int getQuantityMoreThan(String color, int cottonPart) {
		return (int) jdbcTemplate.query("select sum(quantity) quantity from socks where color = ? and cotton_part > ?",
				new Object[] {colorsDAO.get(color).getId(), cottonPart},
				new SocksQuantityMapper()).stream().findAny().orElse(0);
	}
	
	public int getQuantityLessThan(String color, int cottonPart) {
		return (int) jdbcTemplate.query("select sum(quantity) quantity from socks where color = ? and cotton_part < ?",
				new Object[] {colorsDAO.get(color).getId(), cottonPart},
				new SocksQuantityMapper()).stream().findAny().orElse(0);
	}
}
