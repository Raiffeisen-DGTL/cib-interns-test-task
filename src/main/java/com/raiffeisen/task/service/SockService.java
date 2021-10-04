package com.raiffeisen.task.service;

import com.raiffeisen.task.repository.SockRepository;
import org.springframework.stereotype.Service;

@Service
public class SockService {
    private final SockRepository repository;

    public SockService(SockRepository repository) {
        this.repository = repository;
    }


}
