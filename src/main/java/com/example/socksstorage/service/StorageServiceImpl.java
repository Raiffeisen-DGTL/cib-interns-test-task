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
    public boolean create(String color, int cottonPart, int quantity) {
        List<Socks> socksList = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            Socks sock = new Socks();
            sock.setColor(color);
            sock.setCottonPart(cottonPart);
            socksList.add(sock);
        }
        socksRepository.saveAll(socksList);
        return true;
    }

    @Override
    public int readAll(String color, Socks.Operation operation, int cottonPart) {
        switch (operation) {
            case moreThan :
                return socksRepository.countAllByColorAndCottonPartGreaterThan(color, cottonPart);
            case lessThan:
                return socksRepository.countAllByColorAndCottonPartLessThan(color, cottonPart);
            default:
                return socksRepository.countAllByColorAndCottonPart(color, cottonPart);
        }
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
    public boolean delete(String color, int cottonPart, int quantity) {
        List<Socks> socksList = socksRepository.findAllByColorAndCottonPart(color, cottonPart);
        if (socksList.size() < quantity) {
            return false;
        }
        List<Socks> socksListToRemove = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            socksListToRemove.add(socksList.get(i));
        }
        socksRepository.deleteAll(socksListToRemove);
        return true;
    }
}
