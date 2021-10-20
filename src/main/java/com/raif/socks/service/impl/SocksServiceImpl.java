package com.raif.socks.service.impl;

import com.raif.socks.dto.SocksDto;
import com.raif.socks.entity.SocksEntity;
import com.raif.socks.repository.SocksRepository;
import com.raif.socks.repository.specification.SocksSpecification;
import com.raif.socks.service.Operation;
import com.raif.socks.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;
    private final SocksSpecification socksSpecification;

    @Override
    public boolean income(SocksDto socksDto) {

        Optional<SocksEntity> socksEntityOptional = socksRepository.findOneByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());

        if (socksEntityOptional.isPresent()) {
            socksEntityOptional.get().setQuantity(socksEntityOptional.get().getQuantity() + socksDto.getQuantity());
            socksRepository.save(socksEntityOptional.get());

        } else {
            SocksEntity socksEntity = new SocksEntity();
            socksEntity.setColor(socksDto.getColor());
            socksEntity.setCottonPart(socksDto.getCottonPart());
            socksEntity.setQuantity(socksDto.getQuantity());
            SocksEntity savedSocksEntity = socksRepository.save(socksEntity);
        }

        return true;
    }

    @Override
    public boolean outcome(SocksDto socksDto) {
        Optional<SocksEntity> socksEntityOptional = socksRepository.findOneByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());

        if (socksEntityOptional.isPresent()) {
            int quantity = socksEntityOptional.get().getQuantity();
            if (quantity < socksDto.getQuantity()) {
                throw new IllegalArgumentException("Present quantity " + quantity + " is less than outcome " + socksDto.getQuantity());
            }
            socksEntityOptional.get().setQuantity(quantity - socksDto.getQuantity());
            socksRepository.save(socksEntityOptional.get());
        } else {
            return false;
        }

        return true;
    }

    @Override
    public int find(String color, Operation operation, int cottonPart) {
        Specification<SocksEntity> specification = Specification.where(socksSpecification.getQuantity(color, operation.name(), cottonPart));
        List<SocksEntity> socksList = socksRepository.findAll(specification);
        return socksList.stream()
                .mapToInt(SocksEntity::getQuantity)
                .sum();
    }

}
