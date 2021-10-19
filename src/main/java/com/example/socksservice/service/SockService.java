package com.example.socksservice.service;

import com.example.socksservice.exceptions.ApiInvalidParametersException;
import com.example.socksservice.model.Operation;
import com.example.socksservice.model.Sock;
import com.example.socksservice.repository.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class SockService {
    private final SockRepository sockRepository;

    @Autowired
    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }


    @Transactional
    public void income(Sock sock){

        if(sock.getCottonPart()>=0 && sock.getCottonPart()<=100 && sock.getQuantity()>0){
            Optional<Sock> sockOpt = sockRepository.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
            if(sockOpt.isPresent()){
                Sock findSock = sockOpt.get();
                findSock.setQuantity(findSock.getQuantity()+sock.getQuantity());
            }
            else{
                sockRepository.save(sock);
            }
        }else{
            throw new ApiInvalidParametersException("The request parameters are missing or have an incorrect format!");
        }
    }

    @Transactional
    public void outcome(Sock sock){
        Optional<Sock> findSocksOpt = sockRepository
                .findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if(findSocksOpt.isPresent()){
            Sock findSock = findSocksOpt.get();
            if(sock.getQuantity()<=findSock.getQuantity()){
                findSock.setQuantity(findSock.getQuantity()-sock.getQuantity());
            }else{
                throw new ApiInvalidParametersException("Incorrect number of pairs to be debited!");
            }
        }else{
            throw new ApiInvalidParametersException("The request parameters are missing or have an incorrect format!");
        }
    }


    @Transactional
    public int count(String color, String operation, int cottonPart) {
        Optional<Integer> countOpt = Optional.empty();
        if(cottonPart>=0 && cottonPart<=100){

            try {
                if (Operation.valueOf(operation) == Operation.lessThan) {

                    countOpt = sockRepository.findCountLessThan(cottonPart, color);

                } else if (Operation.valueOf(operation) == Operation.moreThan) {

                    countOpt = sockRepository.findCountMoreThan(cottonPart, color);

                } else if (Operation.valueOf(operation) == Operation.equal) {

                    countOpt = sockRepository.findCountEqual(cottonPart, color);
                }
            }catch (IllegalArgumentException ex){
                throw new ApiInvalidParametersException("Invalid comparison operation entered!");
            }

        }else{
            throw new ApiInvalidParametersException("The request parameters are missing or have an incorrect format!");
        }

        return countOpt.orElse(0);
    }
}
