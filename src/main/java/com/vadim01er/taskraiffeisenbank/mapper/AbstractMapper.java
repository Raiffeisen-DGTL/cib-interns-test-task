package com.vadim01er.taskraiffeisenbank.mapper;

import org.modelmapper.ModelMapper;

import java.util.Objects;

public abstract class AbstractMapper<E, D> implements Mapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    private final ModelMapper mapper;

    protected AbstractMapper(ModelMapper mapper, Class<E> entityClass, Class<D> dtoClass) {
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D updateDto(D updated, E recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E updateEntity(E updated, D recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }
}
