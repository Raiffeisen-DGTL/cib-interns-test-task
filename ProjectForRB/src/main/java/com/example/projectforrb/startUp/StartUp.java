package com.example.projectforrb.startUp;

import com.example.projectforrb.model.SocksEntity;
import com.example.projectforrb.repository.SocksRepository;
import com.example.projectforrb.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class StartUp implements CommandLineRunner {

    private final SocksRepository repo;
    @Override
    public void run(String... args) throws Exception {
//        SocksEntity entity = new SocksEntity();
//        entity.setId(UUID.randomUUID());
//        entity.setQuantity(150);
//        entity.setColor("green");
//        entity.setCottonPart(70);
//        repo.save(entity);
    }
}
