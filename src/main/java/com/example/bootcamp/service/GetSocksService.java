package com.example.bootcamp.service;

import com.example.bootcamp.dto.Operation;
import com.example.bootcamp.entity.SocksEntity;

import java.util.List;

public interface GetSocksService {

    int getSocks(String color, Operation operation, short cotton);
}
