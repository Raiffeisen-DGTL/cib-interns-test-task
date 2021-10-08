package ru.danilarassokhin.raiffeisensocks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.danilarassokhin.raiffeisensocks.dto.CottonPartEqualityOperations;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.mapper.SocksMapper;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.object.ValidationResult;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;
import ru.danilarassokhin.raiffeisensocks.util.ValidationUtils;

import java.util.Arrays;

@Service
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public Socks findByColorAndCottonPartIs(String color, byte cottonPart) {
        return socksRepository.findByColorAndCottonPartIs(color, cottonPart).orElse(null);
    }

    @Override
    public SocksIncomeDto income(SocksIncomeDto socksIncomeDto) throws DataValidityException,
            InternalException {
        ValidationResult validationResult = ValidationUtils.validate(socksIncomeDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        Socks result = socksRepository.findByColorAndCottonPartIs(
                socksIncomeDto.getColor(),
                socksIncomeDto.getCottonPart()
        ).orElse(null);
        if(result == null) {
            result = SocksMapper.INSTANCE.incomeDtoToSocks(socksIncomeDto);
        }else{
            result.addQuantity(
                    socksIncomeDto.getQuantity()
            );
        }
        try {
            result = socksRepository.save(result);
            socksIncomeDto.setQuantity(
                    result.getQuantity()
            );
            return socksIncomeDto;
        }catch (DataAccessException e) {
            throw new InternalException("Internal error has occurred");
        }
    }

    @Override
    public SocksOutcomeDto outcome(SocksOutcomeDto socksOutcomeDto) throws DataValidityException,
            DataNotExistsException, InternalException {
        ValidationResult validationResult = ValidationUtils.validate(socksOutcomeDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        Socks result = socksRepository.findByColorAndCottonPartIs(
                socksOutcomeDto.getColor(),
                socksOutcomeDto.getCottonPart()
        ).orElse(null);
        if(result == null) {
            throw new DataNotExistsException("There is no socks exists with given color (" + socksOutcomeDto.getColor()
            + ") and cotton part (" + socksOutcomeDto.getCottonPart() + ")!");
        }
        if(result.getQuantity() < socksOutcomeDto.getQuantity()) {
            throw new DataNotExistsException("There is no socks left with give color (" + socksOutcomeDto.getColor()
                    + ") and cotton part (" + socksOutcomeDto.getCottonPart() + ")! "
            + "Socks in stock: " + result.getQuantity() + ", tried to outcome: " +
                    socksOutcomeDto.getQuantity());
        }
        try {
            result.addQuantity(
                    -socksOutcomeDto.getQuantity()
            );
            result = socksRepository.save(result);
            socksOutcomeDto.setQuantity(
                    result.getQuantity()
            );
            return socksOutcomeDto;
        }catch (DataAccessException e) {
            throw new InternalException("Internal error has occurred");
        }
    }

    @Override
    public Long countSocks(SocksSearchDto socksSearchDto) throws DataValidityException {
        ValidationResult validationResult = ValidationUtils.validate(socksSearchDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        CottonPartEqualityOperations operations;
        try{
            operations = Enum.valueOf(CottonPartEqualityOperations.class, socksSearchDto.getOperation());
        }catch (IllegalArgumentException e) {
            throw new DataValidityException("Operation " + socksSearchDto.getOperation() + " doesn't exists! "
            + "Correct operations are: " + Arrays.toString(CottonPartEqualityOperations.values()));
        }
        switch (operations) {
            case moreThan:
                return socksRepository.countByColorAndCottonPartGreaterThan(
                        socksSearchDto.getColor(),
                        socksSearchDto.getCottonPart()
                ).orElse(0L);
            case equal:
                Socks result = findByColorAndCottonPartIs(
                        socksSearchDto.getColor(),
                        socksSearchDto.getCottonPart()
                );
                if(result == null) {
                    return 0L;
                }
                return 1L;
            case lessThan:
                return socksRepository.countByColorAndCottonPartLessThan(
                        socksSearchDto.getColor(),
                        socksSearchDto.getCottonPart()
                ).orElse(0L);
        }
        return 0L;
    }
}
