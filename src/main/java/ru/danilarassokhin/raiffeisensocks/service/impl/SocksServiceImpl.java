package ru.danilarassokhin.raiffeisensocks.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.danilarassokhin.raiffeisensocks.dto.SocksResponseDto;
import ru.danilarassokhin.raiffeisensocks.mapper.SocksMapper;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.object.ValidationResult;
import ru.danilarassokhin.raiffeisensocks.repository.CottonPartEqualityOperation;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;
import ru.danilarassokhin.raiffeisensocks.service.dto.*;
import ru.danilarassokhin.raiffeisensocks.util.ValidationUtils;

/**
 * Implementation of {@link SocksService}.
 */
@Service
public class SocksServiceImpl implements SocksService {

  private final SocksRepository socksRepository;

  @Autowired
  public SocksServiceImpl(SocksRepository socksRepository) {
    this.socksRepository = socksRepository;
  }

  @Override
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public Socks findByColorAndCottonPartIs(String color, byte cottonPart) {
    return socksRepository.findByColorAndCottonPartIs(color, cottonPart).orElse(null);
  }

  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public SocksServiceResponse income(SocksServiceIncomeDto socksIncomeDto) {
    SocksServiceResponse socksServiceResponse = new SocksServiceResponse();
    ValidationResult validationResult = ValidationUtils.validate(socksIncomeDto);
    if (!validationResult.isValid()) {
      socksServiceResponse.setStatus(ServiceResponseStatus.INVALID_DATA);
      socksServiceResponse.setMessage(validationResult.getFirstErrorMessage());
      return socksServiceResponse;
    }
    Socks result = socksRepository.findByColorAndCottonPartIs(
        socksIncomeDto.getColor(),
        socksIncomeDto.getCottonPart()
    ).orElse(null);
    if (result == null) {
      result = SocksMapper.INSTANCE.incomeDtoToSocks(socksIncomeDto);
    } else {
      result.addQuantity(
          socksIncomeDto.getQuantity()
      );
    }
    try {
      result = socksRepository.save(result);
      SocksResponseDto socksResponseDto = new SocksResponseDto(
          result.getColor(),
          result.getCottonPart(),
          result.getQuantity()
      );
      socksServiceResponse.setPayload(socksResponseDto);
      return socksServiceResponse;
    } catch (DataAccessException e) {
      LoggerService.error("Internal error occurred: " + e.getMessage());
      socksServiceResponse.setStatus(ServiceResponseStatus.INTERNAL_ERROR);
    }
    return socksServiceResponse;
  }

  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public SocksServiceResponse outcome(SocksServiceOutcomeDto socksOutcomeDto) {
    SocksServiceResponse socksServiceResponse = new SocksServiceResponse();
    ValidationResult validationResult = ValidationUtils.validate(socksOutcomeDto);
    if (!validationResult.isValid()) {
      socksServiceResponse.setStatus(ServiceResponseStatus.INVALID_DATA);
      socksServiceResponse.setPayload(validationResult.getFirstErrorMessage());
      return socksServiceResponse;
    }
    Socks result = socksRepository.findByColorAndCottonPartIs(
        socksOutcomeDto.getColor(),
        socksOutcomeDto.getCottonPart()
    ).orElse(null);
    if (result == null) {
      socksServiceResponse.setStatus(ServiceResponseStatus.NO_SOCKS_EXIST);
      socksServiceResponse.setPayload("There is no socks exists with given color ("
          + socksOutcomeDto.getColor()
          + ") and cotton part (" + socksOutcomeDto.getCottonPart() + ")!");
      return socksServiceResponse;
    }
    if (result.getQuantity() < socksOutcomeDto.getQuantity()) {
      socksServiceResponse.setStatus(ServiceResponseStatus.NO_SOCKS_LEFT);
      socksServiceResponse.setMessage("There is no socks left with give color ("
          + socksOutcomeDto.getColor()
          + ") and cotton part (" + socksOutcomeDto.getCottonPart() + ")! "
          + "Socks in stock: " + result.getQuantity() + ", tried to outcome: "
          + socksOutcomeDto.getQuantity());
      return socksServiceResponse;
    }
    try {
      result.addQuantity(
          -socksOutcomeDto.getQuantity()
      );
      result = socksRepository.save(result);
      SocksResponseDto socksResponseDto = new SocksResponseDto(
          result.getColor(),
          result.getCottonPart(),
          result.getQuantity()
      );
      socksServiceResponse.setPayload(socksResponseDto);
    } catch (DataAccessException e) {
      LoggerService.error("Internal error occurred: " + e.getMessage());
      socksServiceResponse.setStatus(ServiceResponseStatus.INTERNAL_ERROR);
      socksServiceResponse.setPayload("Internal error has occurred");
    }
    return socksServiceResponse;
  }

  @Override
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public SocksServiceResponse countSocks(SocksServiceSearchDto socksSearchDto) {
    SocksServiceResponse socksServiceResponse = new SocksServiceResponse();
    ValidationResult validationResult = ValidationUtils.validate(socksSearchDto);
    if (!validationResult.isValid()) {
      socksServiceResponse.setStatus(ServiceResponseStatus.INVALID_DATA);
      socksServiceResponse.setPayload(validationResult.getFirstErrorMessage());
      return socksServiceResponse;
    }
    Specification<Socks> searchQuery = SocksRepository.ofColor(
        socksSearchDto.getColor()
    );
    CottonPartEqualityOperation operation = CottonPartEqualityOperation.getByCode(
        socksSearchDto.getOperation()
    ).orElse(null);
    if(operation == null) {
      socksServiceResponse.setStatus(ServiceResponseStatus.INVALID_DATA);
      socksServiceResponse.setMessage("Operation " + socksSearchDto.getOperation()
          + " doesn't exists! "
          + "Correct operations are: "
          + Arrays.toString(
          CottonPartEqualityOperation.codes()
              .toArray()
      ));
      return socksServiceResponse;
    }
    if (operation.hasSpecification()) {
      searchQuery = searchQuery.and(
          operation.create(
              socksSearchDto.getCottonPart()
          )
      );
    }

    List<Socks> countable = socksRepository.findAll(searchQuery);
    Long socksCount = countable.stream()
        .reduce(0L,
            (r, s) -> r + s.getQuantity(),
            Long::sum
        );
    socksServiceResponse.setPayload(socksCount);
    return socksServiceResponse;
  }
}
