package com.example.testtask.services;

import com.example.testtask.dto.SocksDto;
import com.example.testtask.store.entities.SocksEntity;
import com.example.testtask.store.repositories.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;


    public SocksEntity save(SocksDto socksDto) {
        SocksEntity socks = SocksEntity.builder()
                //.id(Integer)
                .color(socksDto.getColor())
                .cottonPart(socksDto.getCottonPart())
                .quantity(socksDto.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
        return socksRepository.saveAndFlush(socks);
    }

    public int getAll(){
        return socksRepository.findAll().size();
    }
}
