package ru.danilax86.raiftest.services;

import org.springframework.data.repository.CrudRepository;
import ru.danilax86.raiftest.models.Socks;

public interface SocksStorage extends CrudRepository<Socks, Integer> {
	Iterable<Socks> findByColor(String color);
}
