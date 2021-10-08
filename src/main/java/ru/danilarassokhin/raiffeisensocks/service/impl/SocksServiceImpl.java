package ru.danilarassokhin.raiffeisensocks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.mapper.SocksMapper;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.object.ValidationResult;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;
import ru.danilarassokhin.raiffeisensocks.util.ValidationUtils;

@Service
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public Socks findByColorAndCottonPartGreaterThan(String color, byte cottonPart) {
        return null;
    }

    @Override
    public Socks findByColorAndCottonPartLessThan(String color, byte cottonPart) {
        return null;
    }

    @Override
    public Socks findByColorAndCottonPartIs(String color, byte cottonPart) {
        return null;
    }

    @Override
    public void income(SocksIncomeDto socksIncomeDto) throws DataValidityException {
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
        socksRepository.save(result);
    }

    @Override
    public void outcome(SocksOutcomeDto socksOutcomeDto) throws DataValidityException, DataNotExistsException {
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
        if(result.getQuantity() <= 0) {
            throw new DataNotExistsException("There is no socks left with give color (" + socksOutcomeDto.getColor()
                    + ") and cotton part (" + socksOutcomeDto.getCottonPart() + ")!");
        }
        result.addQuantity(
                -socksOutcomeDto.getQuantity()
        );
        socksRepository.save(result);
    }
}
