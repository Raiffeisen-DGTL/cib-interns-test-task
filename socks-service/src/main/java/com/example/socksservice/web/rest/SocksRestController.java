package com.example.socksservice.web.rest;

import com.example.socksservice.dto.CountOperation;
import com.example.socksservice.dto.SockDTO;
import com.example.socksservice.entity.Sock;
import com.example.socksservice.mapper.SocksMapper;
import com.example.socksservice.service.SocksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.awt.image.ConvolveOp;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/socks")
public class SocksRestController {
    private static final Logger logger = LoggerFactory.getLogger(SocksRestController.class);

    private final SocksMapper mapper;
    private final SocksService socksService;

    public SocksRestController(SocksMapper mapper, SocksService socksService) {
        this.mapper = mapper;
        this.socksService = socksService;
    }

    @PostMapping(value = "income", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SockDTO> income(@RequestBody @Validated SockDTO dto) {
        Sock sock = mapper.fromDto(dto);

        SockDTO response = mapper.fromEntity(socksService.increaseQuantity(sock, sock.getQuantity()));
        logger.info("Пополнили запас носков {}", response);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "outcome", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SockDTO> outcome(@RequestBody @Validated SockDTO dto) {
        Sock sock = mapper.fromDto(dto);

        SockDTO response = mapper.fromEntity(socksService.decreaseQuantity(sock, sock.getQuantity()));
        logger.info("Осталось на складе {}", response);

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count(@RequestParam("color") String color,
                                      @RequestParam("operation") String operation,
                                      @RequestParam("cottonPart") int cottonPart) {
        CountOperation countOperation = CountOperation.createFromString(operation);
        Long socksCount = socksService.getSocksCount(color, cottonPart, countOperation);

        logger.info("Носков у нас {}", socksCount);

        return ResponseEntity.ok(socksCount);
    }
}
