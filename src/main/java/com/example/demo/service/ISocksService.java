package com.example.demo.service;

import com.example.demo.dto.SocksDto;
import javassist.NotFoundException;
import lombok.NonNull;


public interface ISocksService {
    Long getByColorAndCottonPart(@NonNull String color, @NonNull String operation, @NonNull Long cottonPart);

    void add(@NonNull SocksDto socksDto);

    void reduce(@NonNull SocksDto socksDto) throws SocksException, NotFoundException;
}
