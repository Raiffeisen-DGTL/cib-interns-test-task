package com.example.testtask.controllers;

//import com.example.testtask.store.repositories.SocksRepository;

import com.example.testtask.dto.SocksDto;
import com.example.testtask.exceptions.BadRequestException;
import com.example.testtask.exceptions.NotFoundException;
import com.example.testtask.factories.SocksDtoFactory;
import com.example.testtask.services.SocksService;
import com.example.testtask.store.entities.SocksEntity;
import com.example.testtask.store.repositories.SocksRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Transactional
@RestController
@RequestMapping("/api/socks")
public class SocksController {

  private final SocksService socksService;
  SocksRepository socksRepository;
  //SocksDtoFactory socksDtoFactory;
  private static final String SOCKS_INCOME = "/api/socks/income";
  private static final String SOCKS_OUTCOME = "/api/socks/outcome";
  private static final String GET_SOCKS = "/api/socks";

  @PostMapping("/income")
  public SocksEntity incomeSocks(@RequestBody Optional<SocksDto> optionalSocks) {
//        if (optionalSocks.get().getColor().isEmpty()) {
//            //throw new BadRequestException(" ");
//        }
//        SocksEntity socks = socksRepository.saveAllAndFlush(
//                SocksEntity.builder()
//                .color()
//        )\
    Optional<String> socksColor = Optional.of(optionalSocks.get().getColor());
    final SocksEntity socks = optionalSocks
        .map(this::getSocksOrThrowException)
        .orElseGet(() -> SocksEntity.builder().build());

    socks.setColor(socksColor.get());
    System.out.println(socks.getColor());
    socks.setCottonPart(optionalSocks.get().getCottonPart());
    socks.setQuantity(socks.getQuantity() + optionalSocks.get().getQuantity());
    if (socks.getId() == 0) {
      var time = LocalDateTime.now();
      int id = time.getYear() + time.getMonthValue() + time.getMinute() + time.getNano();
      socks.setId(id);
    }
    socks.setCreatedAt(LocalDateTime.now());
//        socksColor
//                .ifPresent(color -> {
//                    socksRepository
//                            .findAll(color)
//                            .stream()
//                            .filter(anotherSocks -> !Objects.equals(anotherSocks.getColor(), socks.getColor()))
//                            .
//                    socks.setColor(color);
//                });
//        final SocksEntity savedSocks = socksRepository.saveAndFlush(socks);//socksService.save(socks);
//        return savedSocks;//socksService.save(socks);
    return socksRepository.saveAndFlush(socks);
  }

  private SocksEntity getSocksOrThrowException(/*Optional<String> color, Optional<Integer> cottonPart*/
      SocksDto socks) {
    var color = Optional.of(socks.getColor());
    var cottonPart = Optional.of(socks.getCottonPart());
    return socksRepository
        .findAllByColorAndEqualCottonPart(color, cottonPart)//.get();
        .orElseGet(() -> SocksEntity.builder().build());
    //.orElseThrow(() -> new NotFoundException(String.format("Socks with %s color do not exist", color)));
  }

  @PostMapping("/outcome")
  public SocksEntity outcomeSocks(@RequestBody Optional<SocksDto> optionalSocks) {

    Optional<String> socksColor = Optional.of(optionalSocks.get().getColor());
    final SocksEntity socks = optionalSocks
        .map(this::getSocksOrThrowException)
        .orElseGet(() -> SocksEntity.builder().build());

    socks.setColor(socksColor.get());
    System.out.println(socks.getColor());
    socks.setCottonPart(optionalSocks.get().getCottonPart());
    socks.setQuantity(socks.getQuantity() - optionalSocks.get().getQuantity());
    if (socks.getId() == 0) {
      var time = LocalDateTime.now();
      int id = time.getYear() + time.getMonthValue() + time.getMinute() + time.getNano();
      socks.setId(id);
    }

    socks.setCreatedAt(LocalDateTime.now());
    if (socks.getQuantity() < 0) {
      throw new NotFoundException("sheeeesh");
    }
    return socksRepository.saveAndFlush(socks);
  }

  @GetMapping()
  public String getSocks(@RequestParam("color") Optional<String> socksColor,
      @RequestParam("operation") Optional<String> operation,
      @RequestParam("cottonPart") Optional<Integer> socksCotton) {
    //var filteredStream = socksCotton.map(socksRepository::streamAllByCottonPart);
    //final List<SocksDto> temp = Collections.emptyList();
    long count = 0;
    List<SocksEntity> lst = null;
    switch (operation.get()) {
      case "moreThan":
        lst = socksRepository.findAllByColorAndMoreCottonPart(socksColor, socksCotton);
        break;
      case "lessThan":
        lst = socksRepository.findAllByColorAndLessCottonPart(socksColor, socksCotton);
        break;
      case "equals":
        lst = socksRepository.findAllByColorAndEqualCottonPart(socksColor, socksCotton).stream()
            .collect(Collectors.toList());
        break;
      default:
        throw new BadRequestException("Invalid operation");
    }
    String res = String.valueOf(lst.stream().mapToLong(SocksEntity::getQuantity).sum());
    return res;
//        socksColor.ifPresent(color-> {
//            final List<SocksEntity> temp =  socksRepository
//                            .findAll()
//                            .stream()
//                            .filter(anotherSocks -> {
//                                switch (operation.get()) {
//                                    case "moreThen":
//                                        return socks.getCottonPart() > anotherSocks.getCottonPart();
//                                    case "lessThen":
//                                        return socks.getCottonPart() < anotherSocks.getCottonPart();
//                                    case "equals":
//                                        return socks.getCottonPart().equals(anotherSocks.getCottonPart());
//                                    default:
//                                        throw new BadRequestException("Invalid operation");
//                                }
//                            }).collect(Collectors.toList());//map(socksDtoFactory::makeSocksDto).collect(Collectors.toList());
//
//              long cnt = temp.stream().mapToLong(SocksEntity::getQuantity).sum();
//              socks.setQuantity(cnt);
//        });

    //var count = socksService.getAll();//socksRepository.findAll().size();
    //return socks.getQuantity().toString();
  }


}
