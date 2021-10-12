package com.oleg.socks.service;

import com.oleg.socks.dto.SocksDto;
import com.oleg.socks.entity.SocksEntity;
import com.oleg.socks.exception.NotFoundException;
import com.oleg.socks.exception.NotValidInputException;
import com.oleg.socks.repository.SocksRepository;
import com.oleg.socks.validator.CottonPartValidator;
import com.oleg.socks.validator.OperationValidator;
import com.oleg.socks.validator.SocksDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;

    @Value("${operator.greaterThan}")
    private String greaterThanOperator;
    @Value("${operator.lessThan}")
    private String lessThanOperator;
    @Value("${operator.equal}")
    private String equalOperator;

    public void addSocks(SocksDto socksDto) {
        SocksDtoValidator.validateSocksDto(socksDto);

        String color = socksDto.getColor();
        Integer cottonPart = Integer.parseInt(socksDto.getCottonPart());
        Long quantity = Long.parseLong(socksDto.getQuantity());

        Optional<SocksEntity> socksEntityOptional = socksRepository.findByColorAndCottonPart(color, cottonPart);
        SocksEntity socksEntity;

        if (socksEntityOptional.isEmpty()) {
            socksEntity = new SocksEntity();
            socksEntity.setColor(color);
            socksEntity.setCottonPart(cottonPart);
            socksEntity.setQuantity(quantity);
        } else {
            socksEntity = socksEntityOptional.get();
            socksEntity.setQuantity(socksEntity.getQuantity() + quantity);
        }
        socksRepository.save(socksEntity);
    }

    public void removeSocks(SocksDto socksDto) {

        SocksDtoValidator.validateSocksDto(socksDto);

        Integer cottonPart = Integer.parseInt(socksDto.getCottonPart());
        Long quantity = Long.parseLong(socksDto.getQuantity());

        Optional<SocksEntity> socksEntityOptional = socksRepository.findByColorAndCottonPart(socksDto.getColor(),
                cottonPart);

        if (socksEntityOptional.isEmpty()) {
            throw new NotFoundException("No such socks were found in the storage");
        } else {
            SocksEntity socksEntity = socksEntityOptional.get();
            Long oldQuantity = socksEntity.getQuantity();
            int comparedValue = oldQuantity.compareTo(quantity);

            if (comparedValue < 0) {
                throw new RuntimeException();
            } else if (comparedValue == 0) {
                socksRepository.delete(socksEntity);
            } else {
                socksEntity.setQuantity(oldQuantity - quantity);
                socksRepository.save(socksEntity);
            }
        }

    }

    public Long getSocks(String color, String operation, String stringCottonPart) {
        Long sum = 0L;

        if (!OperationValidator.isValid(operation)) {throw new NotValidInputException("not valid operation");}
        if (!CottonPartValidator.isValid(stringCottonPart)) {throw new NotValidInputException("cotton percentage is not valid");}

        Integer cottonPart = Integer.parseInt(stringCottonPart);

        if (operation.equals(greaterThanOperator)) {
            Optional<List<SocksEntity>> optionalSocksEntities = socksRepository
                    .findAllByColorAndCottonPartIsGreaterThan(color, cottonPart);
            if (optionalSocksEntities.isPresent()) {
                return optionalSocksEntities.get().stream().map(SocksEntity::getQuantity).reduce(0L, Long::sum);
            }
        }
        if (operation.equals(lessThanOperator)) {
            Optional<List<SocksEntity>> optionalSocksEntities = socksRepository
                    .findAllByColorAndCottonPartIsLessThan(color, cottonPart);
            if (optionalSocksEntities.isPresent()) {
                return optionalSocksEntities.get().stream().map(SocksEntity::getQuantity).reduce(0L, Long::sum);
            }
        }
        if (operation.equals(equalOperator)) {
            Optional<SocksEntity> socksEntityOptional = socksRepository.findByColorAndCottonPart(color, cottonPart);
            if (socksEntityOptional.isPresent()) {
                return socksEntityOptional.get().getQuantity();
            }

        }
        return sum;
    }

}

