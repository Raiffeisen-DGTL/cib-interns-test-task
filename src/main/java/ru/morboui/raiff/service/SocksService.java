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
        socksRepository.save(socks);
    }


    public void removeSocks(@NotNull Socks socks) {
        if (socksRepository.findSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isPresent()) {
            Socks socksInBD = socksRepository.findSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(Math.max(socksInBD.getQuantity() - socks.getQuantity() , 0));
            socksRepository.save(socksInBD);
    }
}}
