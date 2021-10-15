package com.test.socksapp.usecase;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.repository.SockRepository;

import java.util.List;

public class GetSocks {
    private SockRepository sockRepo;

    public GetSocks(SockRepository sockRepo) {
        this.sockRepo = sockRepo;
    }

    public List<Sock> execute() {
        return sockRepo.findAll();
    }
}
