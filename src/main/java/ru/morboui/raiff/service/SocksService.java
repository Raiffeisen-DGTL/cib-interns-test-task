package ru.morboui.raiff.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.morboui.raiff.entity.Socks;
import ru.morboui.raiff.repository.SocksRepository;


@Service
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void addNewSocks(@NotNull Socks socks) {
        if(socks.getQuantity() != 0 && socks.getCottonPart() > 0 && socks.getCottonPart()<=100)
        socksRepository.save(socks);
        else throw new IllegalArgumentException("Wrong quantity: " + socks.getQuantity() + " of socks or wrong Cotton part: " + socks.getCottonPart());
    }


    public void reduceSocks(@NotNull Socks socks) {
        if (socksRepository.findSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isPresent()) {
            Socks socksInBD = socksRepository.findSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(Math.max(socksInBD.getQuantity() - socks.getQuantity() , -1));

            if(socksInBD.getQuantity() == -1) {
                throw new IllegalStateException("Not enough socks for the outcome");
            }
            socksRepository.save(socksInBD);
    }
}}
