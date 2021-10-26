package com.alexsimba.rafsocks.Service;

import com.alexsimba.rafsocks.dto.SocksForGet;
import com.alexsimba.rafsocks.dto.SocksForPost;
import com.alexsimba.rafsocks.exception.MyEntityNotFoundException;
import com.alexsimba.rafsocks.model.Socks;
import com.alexsimba.rafsocks.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SocksService {
    @Autowired
    private SocksRepository socksRepository;

    public String getSocksSum(SocksForGet socksForGet) {
        String operation = socksForGet.getOperation();
        String color = socksForGet.getColor();
        Integer cottonPart = socksForGet.getCottonPart();
        if ("moreThan".equals(operation)) {
            Integer count = socksRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
            return String.format("The warehouse has %d socks of %s with a cotton part of %d %%", count, color, cottonPart);
        }
        if ("lessThan".equals(operation)) {
            Integer count = socksRepository.countByColorAndCottonPartLessThan(color, cottonPart);
            return String.format("The warehouse has %d socks of %s with a cotton part of %d %%", count, color, cottonPart);
        }
        if ("equal".equals(operation)) {
            Integer count = socksRepository.countByColorAndCottonPartEquals(color, cottonPart);
            return String.format("The warehouse has %d socks of %s with a cotton part of %d %%", count, color, cottonPart);
        }
        throw new MyEntityNotFoundException();
    }

    public String create(SocksForPost socksForPost) {
        ArrayList<Socks> listSocks = new ArrayList<>();
        int quantity = socksForPost.getQuantity();
        for (int i = quantity; i >= 1; i--) {
            Socks socks = new Socks(socksForPost.getColor(), socksForPost.getCottonPart());
            listSocks.add(socks);
        }
        socksRepository.saveAll(listSocks);
        return "success in adding the income ";
    }


    public String delete(SocksForPost socksForPost) {
        int quantity = socksForPost.getQuantity();
        for (int i = quantity; i >= 1; i--) {
            String color = socksForPost.getColor();
            Integer cottonPart = socksForPost.getCottonPart();
            Socks socks = socksRepository.getTopByColorAndCottonPart(color, cottonPart);
            socksRepository.delete(socks);
        }
        return "success to make the outcome ";
    }
}
