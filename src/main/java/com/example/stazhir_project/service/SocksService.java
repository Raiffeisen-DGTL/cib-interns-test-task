package com.example.stazhir_project.service;

import com.example.stazhir_project.model.Socks;
import com.example.stazhir_project.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public List<Socks> moreThan(int cottonPart, String color){
        return socksRepository.findAll().stream()
                .filter(s -> s.getCottonPart() > cottonPart)
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Socks> lessThan(int cottonPart, String color){
        return socksRepository.findAll().stream()
                .filter(s -> s.getCottonPart() < cottonPart)
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Socks> equal(int cottonPart, String color){
        return socksRepository.findAll().stream().
                filter(s -> s.getCottonPart() == cottonPart)
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Socks findById(int id){
        return socksRepository.getById(id);
    }

    public Socks saveSocks(Socks socks){
        return socksRepository.save(socks);
    }

    public List<Socks> findAll(){
        return socksRepository.findAll();
    }
}
