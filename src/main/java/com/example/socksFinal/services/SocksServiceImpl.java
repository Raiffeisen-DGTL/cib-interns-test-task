package com.example.socksFinal.services;

import com.example.socksFinal.model.SocksApp;
import com.example.socksFinal.repos.SocksRepository;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {

    SocksRepository repository;


    public SocksServiceImpl(SocksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SocksApp> getSocks() {
        List<SocksApp> socks = new ArrayList<>();
        repository.findAll().forEach(socks::add);
        return socks;
    }

    @Override
    public void insert(List<SocksApp> socks) {
        for (SocksApp sock : socks) {
            repository.save(sock);

        }
    }

    @Override
    public boolean deleteSocks(List<SocksApp> socks) {
        Map<Integer, Integer> cottonCounter = new HashMap<>(); //считаем все носочки по видам
        Map<String, Map<Integer, Integer>> numCounter = new HashMap<>();
        for (SocksApp sock : socks) {
            String color = sock.getColor();
            int cotton = sock.getCottonPart();
            if (numCounter.containsKey(color)) {
                if (cottonCounter.containsKey(cotton)) {
                    cottonCounter.put(cotton, cottonCounter.get(cotton) + 1);
                }
            }
            cottonCounter.put(cotton, cottonCounter.get(cotton) + 1);
            numCounter.put(color, cottonCounter);
        }

        for(String color : numCounter.keySet()) {
            for (int cotton : cottonCounter.keySet()) {
                if (numCounter.get(color).get(cotton) > repository.countSocksBy(color, cotton)) { //если у нас меньше носочков, чем надо
                    return false;                                                                 //не положим все
                } else {
                    for (SocksApp sock : socks) {
                        repository.delete(sock);
                    }
                }
            }
        }
        return true;
    }

    public int countSocksWithCottonMore (String color, int num) {
        return repository.countSocksWithCottonMore(color, num);
    }

    public int countSocksWithCottonLess (String color, int num) {
        return repository.countSocksWithCottonLess(color, num);
    }

    public int countSocksBy (String color, int num) {
        return repository.countSocksBy(color, num);
    }
}
