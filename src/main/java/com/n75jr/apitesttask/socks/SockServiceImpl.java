package com.n75jr.apitesttask.socks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    @Autowired
    public SockServiceImpl(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Override
    public long getSize() {
        return sockRepository.count();
    }

    @Override
    public int income(Sock sock) {
        return sockRepository.income(sock.getColor(), sock.getCottonPart());
    }

    @Override
    public int outcome(Sock sock) {
        return sockRepository.outcome(sock.getColor(), sock.getCottonPart());
    }

    @Override
    public void outcomeWithoutId(String color, int cotton_part) {
        System.out.println(sockRepository.outcomeWithoutId(color, cotton_part));
        sockRepository.deleteAllById(sockRepository.outcomeWithoutId(color, cotton_part));
    }

    @Override
    public int operMoreThan(String color, int cotton_part) {
        return sockRepository.operMoreThan(color, cotton_part);
    }

    @Override
    public int operLessThan(String color, int cotton_part) {
        return sockRepository.operLessThan(color, cotton_part);
    }

    @Override
    public int operEqual(String color, int cotton_part) {
        return sockRepository.operEqual(color, cotton_part);
    }

    @Override
    public List<Sock> testAll() {
        return sockRepository.findAll();
    }
}
