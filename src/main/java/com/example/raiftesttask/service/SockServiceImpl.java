package com.example.raiftesttask.service;

import com.example.raiftesttask.domain.Color;
import com.example.raiftesttask.domain.Sock;
import com.example.raiftesttask.domain.SockDTO;
import com.example.raiftesttask.repo.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SockServiceImpl implements SockService {
    @Autowired
    SockRepository repository;

    @Override
    public void income(SockDTO sock) {
        Sock s  =repository.findFirstByColorAndCottonPartEquals(sock.getColor(),sock.getCottonPart()).orElse(null);
        if(s!=null){
            Sock res = repository.findById(s.getId()).orElseThrow();
            res.setQuantity(res.getQuantity()+sock.getQuantity());
            repository.save(res);
                return;
        }
        repository.save(Sock.builder().
                cottonPart(sock.cottonPart).
                color(sock.color).
                quantity(sock.getQuantity()).
                build());
    }

    @Override
    public ResponseEntity outcome(SockDTO request) {
        Sock s = repository.findFirstByColorAndCottonPartEquals(request.getColor(),request.getCottonPart()).orElse(null);

        if(s == null) return ResponseEntity.ok().body("Носков с заданными параметрами не найдено!");

        if(s.getQuantity()<=request.getQuantity()){
            Sock res = repository.findById(s.getId()).orElseThrow();
            res.setQuantity(0L);
            return  ResponseEntity.ok().body(s+"Были взяты последние носки:(");
        }

        Sock res = repository.findById(s.getId()).orElseThrow();
        ResponseEntity response = ResponseEntity.ok(request);
        res.setQuantity(res.getQuantity()-request.getQuantity());
        repository.save(res);
        return response;

    }

    @Override
    public List<Sock> findByColorAndCottonPartAfter(Color color, Integer cottonPart) {
     return repository.findByColorAndCottonPartAfter(color,cottonPart);
    }

    @Override
    public List<Sock> findByColorAndCottonPartBefore(Color color, Integer cottonPart) {
        return repository.findByColorAndCottonPartBefore(color,cottonPart);
    }

    @Override
    public Sock findByColorAndCottonPartEquals(Color color, Integer cottonPart) {
        return repository.findFirstByColorAndCottonPartEquals(color,cottonPart).orElse(null);
    }

    @Override
    public List<Sock> findAll() {
        return repository.findAll();
    }
}
