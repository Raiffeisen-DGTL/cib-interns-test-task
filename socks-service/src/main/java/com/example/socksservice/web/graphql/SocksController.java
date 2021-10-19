package com.example.socksservice.web.graphql;

import com.example.socksservice.dto.CountOperation;
import com.example.socksservice.dto.SockDTO;
import com.example.socksservice.entity.Sock;
import com.example.socksservice.mapper.SocksMapper;
import com.example.socksservice.service.SocksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SocksController {
    private static final Logger logger = LoggerFactory.getLogger(SocksController.class);

    private final SocksMapper mapper;
    private final SocksService service;

    public SocksController(SocksMapper mapper, SocksService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @MutationMapping
    public SockDTO increase(@Argument("sock") SockDTO dto) {
        Sock sock = mapper.fromDto(dto);
        return mapper.fromEntity(service.increaseQuantity(sock, sock.getQuantity()));
    }

    @MutationMapping
    public SockDTO decrease(@Argument("sock") SockDTO dto) {
        Sock sock = mapper.fromDto(dto);
        return mapper.fromEntity(service.decreaseQuantity(sock, sock.getQuantity()));
    }

    @QueryMapping
    public Long count(@Argument("color") String color,
                      @Argument("operation") String operation,
                      @Argument("cottonPart") int cottonPart) {
        CountOperation countOperation = CountOperation.createFromString(operation);
        return service.getSocksCount(color, cottonPart, countOperation);
    }
}
