package ru.danilarassokhin.raiffeisensocks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.SockService;

@Service
public class SockServiceImpl implements SockService {

    private SocksRepository socksRepository;

    @Autowired
    public SockServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

}
