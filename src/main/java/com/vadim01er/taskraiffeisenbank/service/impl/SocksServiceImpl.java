package com.vadim01er.taskraiffeisenbank.service.impl;

import com.vadim01er.taskraiffeisenbank.dto.SocksDto;
import com.vadim01er.taskraiffeisenbank.entity.Operation;
import com.vadim01er.taskraiffeisenbank.entity.Socks;
import com.vadim01er.taskraiffeisenbank.mapper.impl.SocksMapper;
import com.vadim01er.taskraiffeisenbank.repository.SocksRepository;
import com.vadim01er.taskraiffeisenbank.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService<SocksDto> {

    private final SocksRepository repository;
    private final SocksMapper mapper;

    @Override
    public List<SocksDto> findByColorAndCottonPart(String color, Short cottonPart, Operation operation) {
        List<Socks> list;
        if (operation == null && cottonPart == null) {
            list = color == null ? repository.findAll(): repository.findByColorLike(color);
        } else if (operation != null && cottonPart != null) {
            switch (operation) {
                case LESS_THAN:
                    list = repository.findByColorLikeAndCottonPartLessThan(color, cottonPart);
                    break;
                case MORE_THAN:
                    list = repository.findByColorLikeAndCottonPartMoreThan(color, cottonPart);
                    break;
                case EQUAL:
                default:
                    list = repository.findByColorLikeAndCottonPartLike(color, cottonPart);
                    break;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return list
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    private Socks findEntityByColorAndCottonPart(String color, Short cottonPart) {
        return repository.findByColorAndCottonPart(color, cottonPart);
    }

    @Override
    public SocksDto income(SocksDto dto) {
        Socks entity = findEntityByColorAndCottonPart(dto.getColor(), dto.getCottonPart());
        if (entity != null) {
            entity.setQuality(entity.getQuality() + dto.getQuality());
            return mapper.toDto(repository.save(entity));
        }
        Socks entity1 = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity1));
    }

    @Override
    public SocksDto delete(SocksDto dto) {
        Socks entity = findEntityByColorAndCottonPart(dto.getColor(), dto.getCottonPart());
        if (entity != null) {
            entity.setQuality(entity.getQuality() - dto.getQuality());
            return mapper.toDto(repository.save(entity));
        }
        return null;
    }
}
