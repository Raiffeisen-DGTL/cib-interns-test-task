package com.raifaisentask.dao;

import com.raifaisentask.data.BadRequestException;

public interface SocksDao {
    Long getSocksQuantityByColor(String color);

    Long getSocksQuantityByCottonPart(String operation, int cottonPart) throws BadRequestException;

    Long getSocksQuantityByColorAndCottonPart(String color, String operation, int cottonPart) throws BadRequestException;

    void addSocks(String color,int cottonPart, Long quantity) throws BadRequestException;

    void removeSocks(String color, int cottonPart, Long quantity) throws BadRequestException;
}
