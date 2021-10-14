package com.example.company.service;

import com.example.company.dao.SockRepository;
import com.example.company.entity.Operation;
import com.example.company.entity.Sock;
import com.example.company.exception.InsufficientDataException;
import com.example.company.exception.NullResultException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SockServiceImpl implements SockService{

    @Autowired
    SockRepository sockRepository;

    @Override
    public Sock getSockByColorAndCottonPart(@NonNull String color, @NonNull Operation operation, @NonNull int cottonPart) {
        switch (operation){
            case lessThan: return sockRepository.getSockByColorAndCottonPartLessThan(color, cottonPart).orElseThrow(()->new NullResultException("Nothing was found for this query"));
            case moreThan: return sockRepository.getSockByColorAndCottonPartGreaterThan(color, cottonPart).orElseThrow(()-> new NullResultException("Nothing was found with this query"));
            case equals: return  sockRepository.getSockByColorAndCottonPartEquals(color, cottonPart).orElseThrow(()-> new NullResultException("Nothing was found with this query"));
            default: throw new InsufficientDataException("Incorrect data");
        }
    }

    @Override
    public void addSocks(@NonNull Sock sock) {
        final Optional<Sock> sockByColorAndCottonPartEquals = sockRepository.getSockByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart());
        if (sockByColorAndCottonPartEquals.isEmpty())
            sockRepository.save(sock);
        else {
            Sock sockDB = sockByColorAndCottonPartEquals.get();
            sockDB.setQuantity(sock.getQuantity()+sockDB.getQuantity());
            sockRepository.save(sockDB);
        }
    }

    @Override
    public void reduceSocks(@NonNull Sock sock) {
        if (sock.getColor()!=null && !sock.getColor().isEmpty() && sock.getCottonPart()!=0 && sock.getQuantity()!=0) {
            final Optional<Sock> sockByColorAndCottonPartEquals = sockRepository.getSockByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart());
            if (sockByColorAndCottonPartEquals.isPresent()) {
                Sock sockDB = sockByColorAndCottonPartEquals.get();

                sockDB.setQuantity(Math.max(sockDB.getQuantity() - sock.getQuantity(), 0));
                sockRepository.save(sockDB);
            }
        }
        else throw  new InsufficientDataException("messaage");
    }
}
