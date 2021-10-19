package com.example.raiftesttask.service;

import com.example.raiftesttask.domain.Color;
import com.example.raiftesttask.domain.Sock;
import com.example.raiftesttask.domain.SockDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SockService {

    void income(SockDTO sock);
    ResponseEntity outcome(SockDTO sock);
    List<Sock> findByColorAndCottonPartAfter(Color color, Integer cottonPart);
    List<Sock> findByColorAndCottonPartBefore(Color color, Integer cottonPart);
    Sock findByColorAndCottonPartEquals(Color color, Integer cottonPart);
    List<Sock> findAll();
}
