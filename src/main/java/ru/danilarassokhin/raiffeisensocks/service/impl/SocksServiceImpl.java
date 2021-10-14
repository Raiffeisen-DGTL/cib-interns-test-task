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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;

/**
 * Implementation of {@link ru.danilarassokhin.raiffeisensocks.service.SocksService}
 */
@Service
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;

    private EntityManager entityManager;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository, EntityManager entityManager) {
        this.socksRepository = socksRepository;
        this.entityManager = entityManager;
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
        long result;
        try{
            operations = Enum.valueOf(CottonPartEqualityOperations.class, socksSearchDto.getOperation());
        }catch (IllegalArgumentException e) {
            throw new DataValidityException("Operation " + socksSearchDto.getOperation() + " doesn't exists! "
                    + "Correct operations are: " + Arrays.toString(CottonPartEqualityOperations.values()));
        }
        try{
            Query query = entityManager.createNamedQuery(operations.getQueryName());
            query.setParameter("cottonPart", socksSearchDto.getCottonPart());
            query.setParameter("color", socksSearchDto.getColor());
            result = ((Number)query.getSingleResult()).longValue();
        }catch (NullPointerException e) {
            result = 0;
        }
        return result;
    }
}
