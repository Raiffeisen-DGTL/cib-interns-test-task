package com.example.socksstorage.service;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    public void create(Socks socks) {
        socksRepository.save(socks);
    }

    @Override
    public List<Socks> readAll() {
        return socksRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        if (socksRepository.existsById(id)) {
            socksRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
