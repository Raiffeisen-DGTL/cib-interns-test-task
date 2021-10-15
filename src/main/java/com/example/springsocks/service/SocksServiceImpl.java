package com.example.springsocks.service;

import com.example.springsocks.domain.Socks;
import com.example.springsocks.enums.OperationType;
import com.example.springsocks.exceptions.ValidationException;
import com.example.springsocks.repository.SocksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * SocksServiceImpl.
 *
 * @author Alexander_Tupchin
 */
@Service
@Transactional
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Long getCountSocks(String color, String operation, Integer cottonPart) {
        if (!validate(color, operation, cottonPart)) {
            throw new ValidationException("Input parameters are incorrect ");
        }

        if(operation.equals(OperationType.EQUAL.getMessage())){
            Optional<Long> countSocks = repository.findCountSockWithParamEquals (color, cottonPart);
            return countSocks.orElse(0L);
        }

        if(operation.equals(OperationType.MORE_THAN.getMessage())){
            Optional<Long> countSocks = repository.findCountSockWithParamMoreThan(color, cottonPart);
            return countSocks.orElse(0L);
        }

        if(operation.equals(OperationType.LESS_THAN.getMessage())){
            Optional<Long> countSocks = repository.findCountSockWithParamLessThan(color, cottonPart);
            return countSocks.orElse(0L);
        }
        return 0L;
    }

    @Override
    @Transactional
    public void addSocks(Socks socks) {
        if (!validate(socks)) {
            throw new ValidationException("Socks parameters incorrect ");
        }
        Optional<Socks> addedSocksOpt = repository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (addedSocksOpt.isPresent()) {
            Socks addedSocks = addedSocksOpt.get();
            addedSocks.setQuantity(addedSocks.getQuantity() + socks.getQuantity());
            repository.save(addedSocks);
        } else {
            repository.save(socks);
        }
    }

    @Override
    @Transactional
    public void reduceSocks(Socks socks) {
        if (!validate(socks)) {
            throw new ValidationException("Socks parameters incorrect ");
        }
        Optional<Socks> addedSocksOpt = repository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (addedSocksOpt.isPresent()) {
            Socks addedSocks = addedSocksOpt.get();
            long quantity = addedSocks.getQuantity() - socks.getQuantity();
            addedSocks.setQuantity(quantity > 0 ? quantity : 0);
            repository.save(addedSocks);
        }
    }

    private boolean validate(Socks socks) {
        return socks.getColor() != null
                && socks.getCottonPart() != null
                && socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100
                && socks.getQuantity() != null
                && socks.getQuantity() >= 0 ;
    }

    private boolean validate(String color, String operation, Integer cottonPart) {
        return color != null
                && operation != null
                && cottonPart != null
                && cottonPart >= 0 && cottonPart <= 100;
    }

}
