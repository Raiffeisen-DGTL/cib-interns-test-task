package com.example.socks.service;

import com.example.socks.db.dto.SocksDTO;
import com.example.socks.db.entity.Socks;
import com.example.socks.db.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.socks.Util.Operations;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;
    private final ModelMapper mapper;

    public long saveSocks(SocksDTO socksDTO) {
        var check = getSocks(socksDTO);

        if (check != null) {
            check.setQuantity(check.getQuantity() + socksDTO.getQuantity());
            return socksRepository.save(check).getId();
        }

        return socksRepository.save(mapper.map(socksDTO, Socks.class)).getId();
    }

    @Transactional
    public void outcome(Socks socks, SocksDTO socksDTO) {
        socks.setQuantity(socks.getQuantity() - socksDTO.getQuantity());
        socksRepository.save(socks);
    }

    public Socks getSocks(SocksDTO socksDTO) {
        return socksRepository.findByCottonPartAndColor(socksDTO.getCottonPart(), socksDTO.getColor());
    }

    public int getSocksByOperation(String color, Operations operation, int cottonPart) {

        switch (operation) {
            case MORETHAN:
                return socksRepository
                        .findByCottonPartGreaterThanAndColorEquals(cottonPart, color)
                        .stream()
                        .mapToInt(Socks::getQuantity)
                        .sum();
            case LESSTHAN:
               return socksRepository
                       .findByCottonPartIsLessThanAndColorEquals(cottonPart, color)
                       .stream()
                       .mapToInt(Socks::getQuantity)
                       .sum();
            case EQUAL:
                return socksRepository
                        .findByCottonPartEqualsAndColorEquals(cottonPart, color)
                        .stream()
                        .mapToInt(Socks::getQuantity)
                        .sum();
        }

        return 0;
    }
}
