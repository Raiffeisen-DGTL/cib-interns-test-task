package com.alekseyfedotov.testtaskraifaizen.service;

import com.alekseyfedotov.testtaskraifaizen.entity.Socks;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocksService {

    ResponseEntity addSocks(Socks socks);

    ResponseEntity removeSocks(Socks socks);

    Integer getCountOfSocks(String color, String operation, int cottonPart);

    List<Socks> getAll();
}
