package ru.pkaranda.cibinternstesttask.mapper;

import java.util.Collection;

public interface BaseMapper<D, DTO> {

    DTO domainToDto(D domain);

    D dtoToDomain(DTO dto);

    Collection<DTO> domainsToDtos(Collection<D> domains);

    Collection<D> dtosToDomains(Collection<DTO> dtos);

}
