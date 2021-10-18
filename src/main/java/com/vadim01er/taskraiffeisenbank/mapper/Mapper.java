package com.vadim01er.taskraiffeisenbank.mapper;

public interface Mapper<E, D> {

    D updateDto(D updated, E recent);

    E updateEntity(E updated, D recent);


    E toEntity(D dto);

    D toDto(E entity);
}
