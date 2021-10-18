package com.vadim01er.taskraiffeisenbank.service;

import com.vadim01er.taskraiffeisenbank.dto.SocksDto;
import com.vadim01er.taskraiffeisenbank.entity.Operation;

import java.util.List;

public interface SocksService<E> {

    List<E> findByColorAndCottonPart(String color, Short cottonPart, Operation operation);

    E income(E dto);

    SocksDto delete(E dto);


}
