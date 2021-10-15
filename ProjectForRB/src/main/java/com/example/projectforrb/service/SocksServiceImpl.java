package com.example.projectforrb.service;


import com.example.projectforrb.model.SocksEntity;
import com.example.projectforrb.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;

    @Override
    public Integer countAllByColorEqualsAndCottonPartIsGreaterThan(String color, Integer cottonPart) {
        return socksRepository.countAllByColorEqualsAndCottonPartIsGreaterThan(color, cottonPart);
    }

    @Override
    public Integer countAllByColorEqualsAndCottonPartIsLessThan(String color, Integer cottonPart) {
        return socksRepository.countAllByColorEqualsAndCottonPartIsLessThan(color, cottonPart);
    }

    @Override
    public Integer countAllByColorEqualsAndCottonPartEquals(String color, Integer cottonPart) {
        return socksRepository.countAllByColorEqualsAndCottonPartEquals(color, cottonPart);
    }

    @Override
    public Optional<SocksEntity> findByColorAndCottonPart(String color, Integer cottonPart) {
        return socksRepository.findByColorAndCottonPart(color, cottonPart);
    }


}
