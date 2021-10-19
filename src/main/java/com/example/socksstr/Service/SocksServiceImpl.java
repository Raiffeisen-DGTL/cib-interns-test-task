package com.example.socksstr.Service;

import com.example.socksstr.Model.Socks;
import com.example.socksstr.Repository.SocksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    SocksRepository socksRepository;


    @Override
    public void save(Socks socks) {
        log.info("IN SocksServiceImpl save {}", socks);
        socksRepository.save(socks);

    }

    @Override
    public void delete(Long id, Socks socks) {
        log.info("IN SocksServiceImpl delete {}");
        List<Socks> socksDelete = socksRepository.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        socksRepository.deleteAll(socksDelete);

    }

    @Override
    public List<Socks> getAll() {
        log.info("IN SocksServiceImpl getAll {}");
        return socksRepository.findAll();
    }

    @Override
    public Socks getById(Long id) {
        return socksRepository.getById(id);
    }


}
