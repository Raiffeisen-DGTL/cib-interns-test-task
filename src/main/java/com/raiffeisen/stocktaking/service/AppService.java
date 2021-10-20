package com.raiffeisen.stocktaking.service;

import com.raiffeisen.stocktaking.dto.SocksModelDTO;
import com.raiffeisen.stocktaking.mapper.ApplicationMapper;
import com.raiffeisen.stocktaking.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;


@Service
@Slf4j
@RequiredArgsConstructor
public class AppService {

    private final AppRepository repository;
    private final ApplicationMapper mapper;
    private final Map<String, BiFunction<String, Integer, Optional<Integer>>> operationMap;


    public boolean createSocksRecord(SocksModelDTO dto) {
        if (dto.getQuantity() == 0) {
            return true;
        }
        var model = mapper.mapToModel(dto);
        try {
            var optionalRow = repository.findSocksModelByColorAndCottonPart(dto.getColor(), dto.getCottonPart());
            if (optionalRow.isPresent()) {
                var row = optionalRow.get();
                row.setQuantity(row.getQuantity() + dto.getQuantity());
                repository.save(row);
            } else {
                repository.save(model);
            }
            return true;
        } catch (Exception e) {
            log.error("Save error - {}", e.getMessage());
            return false;
        }
    }

    public boolean removeSocksRecord(SocksModelDTO dto) {
        if (dto.getQuantity() == 0) {
            return true;
        }
        var cotton = dto.getCottonPart();
        var color = dto.getColor();
        var optionalRow = repository.findSocksModelByColorAndCottonPart(color, cotton);
        if (optionalRow.isPresent()) {
            var row = optionalRow.get();
            if (row.getQuantity() < dto.getQuantity()) {
                // носков на складе меньше чем мы хотим вычесть
                return false;
            } else {
                if (row.getQuantity() == dto.getQuantity()) {
                    repository.delete(row);
                } else {
                    row.setQuantity(row.getQuantity() - dto.getQuantity());
                    repository.save(row);
                }
                return true;
            }
        } else {
            log.warn("Not found data with params color - {} and cotton_part - {}", dto.getColor(), dto.getCottonPart());
            return false;
        }
    }

    public Integer findByParams(String color, String operation, int cottonPart) {
        var validData = validateParams(color, operation, cottonPart);
        if (!validData) {
            return null;
        } else {
            return operationMap.get(operation)
                    .apply(color, cottonPart)
                    .orElse(0);
        }
    }

    private boolean validateParams(String color, String operation, int cottonPart) {
        if (!operationMap.containsKey(operation))
            return false;
        if (color == null)
            return false;
        return cottonPart >= 0 && cottonPart < 101;
    }
}
