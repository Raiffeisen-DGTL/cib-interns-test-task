package com.example.socksstorage.service;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final SocksRepository socksRepository;

    @Override
    public void create(String color, int cottonPart, int quantity) {
        if (cottonPart < 0 ^ cottonPart > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value of cotton part: " + cottonPart);
        }
        for (int i = 0; i < quantity; i++) {
            Socks sock = new Socks();
            sock.setColor(color);
            sock.setCottonPart(cottonPart);
            socksRepository.save(sock);
        }
    }

    @Override
    public List<Socks> readAll(String color, Socks.Operation operation, int cottonPart) {
        List<Socks> socks = socksRepository.findAll();
        if (!"no".equals(color)) {
            socks = socks.stream()
                    .filter(sock -> sock.getColor().equals(color))
                    .collect(Collectors.toList());
        }
        if (operation == Socks.Operation.moreThan) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() > cottonPart)
                    .collect(Collectors.toList());
        } else if (operation == Socks.Operation.lessThan) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() < cottonPart)
                    .collect(Collectors.toList());
        } else if (operation == Socks.Operation.equal) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() == cottonPart)
                    .collect(Collectors.toList());
        }
        return socks;
    }

    @Override
    public boolean deleteById(int id) {
        if (socksRepository.findById(id).isPresent()) {
            socksRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Modifying
//    @Query("delete from socks where socks.color = :color and cottonPart = :part ")
    public boolean delete(@Param("color") String color, Socks.Operation operation,
                          @Param("part") int cottonPart,
                          @Param("quant") int quantity) {
        List<Socks> socks = readAll(color, operation, cottonPart);
        int counter = quantity;
        if (quantity > socks.size()) {
            return false;
        }
        for (Socks sock : socks) {
            if (sock.getColor().equals(color) && sock.getCottonPart() == cottonPart) {
                boolean deleted = deleteById(sock.getId());
                if (!deleted) {
                    return false;
                }
                if (counter == 1) {
                    break;
                }
                counter--;
            }
        }
        return true;
    }
}
