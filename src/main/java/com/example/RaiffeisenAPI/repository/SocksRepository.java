package com.example.RaiffeisenAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RaiffeisenAPI.model.Socks;

public interface SocksRepository extends JpaRepository<Socks, Long>
{
	Socks findByColorAndCottonPart(String color, int cottonPart);
	List<Socks> findByColor(String color);
}
