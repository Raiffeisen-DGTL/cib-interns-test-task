package com.example.testtask.controllers;

//import com.example.testtask.store.repositories.SocksRepository;

import com.example.testtask.dto.SocksDto;
import com.example.testtask.exceptions.BadRequestException;
import com.example.testtask.exceptions.InternalServerError;
import com.example.testtask.exceptions.NotFoundException;
import com.example.testtask.factories.SocksDtoFactory;
import com.example.testtask.services.SocksService;
import com.example.testtask.store.entities.SocksEntity;
import com.example.testtask.store.repositories.SocksRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Transactional
@RestController
@RequestMapping("/api/socks")
public class SocksController {

  //private final SocksService socksService;
  private SocksRepository socksRepository;
  private SocksDtoFactory socksDtoFactory = new SocksDtoFactory();
  private static final String SOCKS_INCOME = "/income";
  private static final String SOCKS_OUTCOME = "/outcome";

  @PostMapping(SOCKS_INCOME)
  public SocksDto incomeSocks(@RequestBody SocksDto socksDto) {
    if (socksDto.getQuantity() <= 0 || socksDto.getColor().trim().isEmpty()
        || socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100) {
      throw new BadRequestException("Invalid parameters");
    }

    Optional<String> socksColor = Optional.of(socksDto.getColor());
    SocksEntity socks = socksRepository
        .findSocksEntityByColorAndCottonPart(socksDto.getColor(),
            socksDto.getCottonPart())
        .orElseGet(() -> SocksEntity.builder().build());

    socks.setColor(socksColor.get());
    socks.setCottonPart(socksDto.getCottonPart());
    socks.setQuantity(socks.getQuantity() + socksDto.getQuantity());
    if (socks.getId() == 0) {
      var time = LocalDateTime.now();
      int id = time.getYear() + time.getMonthValue() + time.getMinute() + time.getNano();
      socks.setId(id);
    }
    socks.setCreatedAt(LocalDateTime.now());
    var savedSocks = socksRepository.saveAndFlush(socks);
    return socksDtoFactory.makeSocksDto(savedSocks);
  }

  @PostMapping(SOCKS_OUTCOME)
  public SocksDto outcomeSocks(@RequestBody SocksDto socksDto) {
    if (socksDto.getQuantity() <= 0 || socksDto.getColor().trim().isEmpty()
        || socksDto.getCottonPart() < 0) {
      throw new BadRequestException("Invalid parameters");
    }
    SocksEntity socks = socksRepository
        .findSocksEntityByColorAndCottonPart(socksDto.getColor(),
            socksDto.getCottonPart())
        .orElseThrow(() -> new InternalServerError("Socks with inserted params do not exist"));

    Optional<String> socksColor = Optional.of(socksDto.getColor());

    socks.setColor(socksColor.get());
    System.out.println(socks.getColor());
    socks.setCottonPart(socksDto.getCottonPart());
    socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
    if (socks.getId() == 0) {
      var time = LocalDateTime.now();
      int id = time.getYear() + time.getMonthValue() + time.getMinute() + time.getNano();
      socks.setId(id);
    }

    socks.setCreatedAt(LocalDateTime.now());
    if (socks.getQuantity() < 0) {
      throw new NotFoundException("Socks quantity can not be a negative value");
    }
    var savedSocks = socksRepository.saveAndFlush(socks);
    return socksDtoFactory.makeSocksDto(savedSocks);
  }

  @GetMapping()
  public String getSocks(@RequestParam("color") String socksColor,
      @RequestParam("operation") String operation,
      @RequestParam("cottonPart") Integer cottonPart) {
    if (socksColor.trim().isEmpty()
        || cottonPart < 0 || cottonPart > 100) {
      throw new BadRequestException("Invalid parameters");
    }
    List<SocksEntity> lst;
    switch (operation) {
      case "moreThan":
        lst = socksRepository
            .findSocksEntitiesByCottonPartGreaterThanAndColorEquals(cottonPart, socksColor)
            .orElseGet(ArrayList::new);
        break;
      case "lessThan":
        lst = socksRepository
            .findSocksEntitiesByCottonPartLessThanAndColorEquals(cottonPart, socksColor)
            .orElseGet(ArrayList::new);
        break;
      case "equals":
        lst = socksRepository
            .findSocksEntitiesByCottonPartEqualsAndColorEquals(cottonPart, socksColor)
            .orElseGet(ArrayList::new);
        break;
      default:
        throw new BadRequestException("Invalid arguments");
    }
    return String.valueOf(lst.stream().mapToLong(SocksEntity::getQuantity).sum());
  }


}
