package com.github.matveyakulov.raifweb.dao;

import com.github.matveyakulov.raifweb.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDAO extends JpaRepository<Sock, Integer> {

    Sock findByColorAndAndCottonPart(String color, int cottonPart);
}
