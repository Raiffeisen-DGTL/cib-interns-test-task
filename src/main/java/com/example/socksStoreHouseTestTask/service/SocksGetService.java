package com.example.socksStoreHouseTestTask.service;

import com.example.socksStoreHouseTestTask.entity.SocksEntity;
import com.example.socksStoreHouseTestTask.repo.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksGetService {

    @Autowired
    private SocksRepo socksRepo;
    private List<SocksEntity> list;
    private int count;
    private int cottonP;

    public Integer getSocks(String color, String operation, String cottonPart) {
        cottonP = Integer.parseInt(cottonPart);
        if (color.equals("") || cottonP < 0 || cottonP > 100 || operation.equals("") || cottonPart.equals("")) {
            throw new IllegalArgumentException();
        }
        switch (operation) {
            case ("moreThan"):
                list = socksRepo.findAllByColorAndCottonPartIsGreaterThan(color, cottonP);
                break;
            case ("equal"):
                list = socksRepo.findAllByColorAndCottonPartEquals(color, cottonP);
                break;
            case ("lessThan"):
                list = socksRepo.findAllByColorAndCottonPartIsLessThan(color, cottonP);
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (list == null) {
            throw new IllegalArgumentException();
        } else {
            count = 0;
            for (SocksEntity socks: list) {
                count += socks.getQuantity();
            }

            return count;
        }
    }

}
