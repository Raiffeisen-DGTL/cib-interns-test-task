package com.mn000009.warehouse.service;

import com.mn000009.warehouse.controller.dto.SocksDto;
import com.mn000009.warehouse.domain.Color;
import com.mn000009.warehouse.domain.Operation;
import com.mn000009.warehouse.domain.SocksPackage;
import com.mn000009.warehouse.exception.IncorrectOperationException;
import com.mn000009.warehouse.exception.NotEnoughSuchSocksException;
import com.mn000009.warehouse.repository.SocksRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SocksServiceImpl implements SocksService {

  private final SocksRepository socksRepository;

  public SocksServiceImpl(SocksRepository socksRepository) {
    this.socksRepository = socksRepository;
  }

  @Override
  public void income(SocksDto receivedSocks) {
    Optional<SocksPackage> currentSocks = socksRepository.findSocksByColor_TitleAndCottonPartEquals(receivedSocks.getColor(),
        receivedSocks.getCottonPart());
    currentSocks.ifPresentOrElse(
        socks -> {
          socks.setQuantity(socks.getQuantity() + receivedSocks.getQuantity());
          socksRepository.save(socks);
        },
        () -> socksRepository.save(new SocksPackage(new Color(receivedSocks.getColor()), receivedSocks.getCottonPart(),
            receivedSocks.getQuantity())));
  }

  @Override
  public void outcome(SocksDto receivedSocks) {
    SocksPackage currentSocksPackage = socksRepository.findSocksByColor_TitleAndCottonPartEqualsAndQuantityGreaterThanEqual(
        receivedSocks.getColor(), receivedSocks.getCottonPart(), receivedSocks.getQuantity())
        .orElseThrow(() -> new NotEnoughSuchSocksException(String.valueOf(receivedSocks.getQuantity())));
    currentSocksPackage.setQuantity(currentSocksPackage.getQuantity() - receivedSocks.getQuantity());
    socksRepository.save(currentSocksPackage);
  }

  @Override
  public String getStatus(String color, Operation operation, int cottonPart) throws IncorrectOperationException {
    String result;
    switch (operation) {
      case EQUAL:
        result = String.valueOf(socksRepository.findAllByColor_TitleAndCottonPartEquals(color, cottonPart).orElse(0));
        break;
      case LESSTHAN:
        result = String.valueOf(socksRepository.findAllByColor_TitleAndCottonPartLessThan(color, cottonPart).orElse(0));
        break;
      case MORETHAN:
        result = String.valueOf(socksRepository.findAllByColor_TitleAndCottonPartGreaterThan(color, cottonPart).orElse(0));
        break;
      default:
        throw new IncorrectOperationException(operation.toString());
    }
    return result;
  }

}
