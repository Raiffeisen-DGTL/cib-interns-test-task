package ru.mytasks.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.mytasks.models.Color;

@Component
public class ColorsDAO {
	private List<Color> colorList = new ArrayList<Color>();
	
	public ColorsDAO (JdbcTemplate jdbcTemplate) {
		colorList = jdbcTemplate.query("select * from colors", new BeanPropertyRowMapper<>(Color.class));
	}
	
	public Color get(int id) {
		return colorList.stream().filter(color -> color.getId() == id).findAny().orElse(null);
	}
	
	public Color get(String name) {
		return colorList.stream().filter(color -> color.getName().equals(name)).findAny().orElse(null);
	}
	
	public boolean contains (String name) {
		return colorList.stream().anyMatch(color -> color.getName().equals(name));
	}
	
	public void print() {
		colorList.stream().forEach(c -> System.out.println(c.getName()));
	}
}
