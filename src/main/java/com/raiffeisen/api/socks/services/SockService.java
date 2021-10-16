package com.raiffeisen.api.socks.services;

import com.raiffeisen.api.socks.entities.Sock;
import com.raiffeisen.api.socks.exceptions.BadArgumentException;
import com.raiffeisen.api.socks.exceptions.NoSuchSocksFoundException;
import com.raiffeisen.api.socks.repositories.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SockService {

    @Autowired
    private SockRepository sockRepository;

    //вернуть список пар носков
    public List<Sock> getSocksByAll(){
        return sockRepository.findAll();
    }

    //получить количество пар носков с указанными параметрами
    public Integer getSocksQuantityByParams(String color, int cottonPart, String operation) throws BadArgumentException{
        Sock sock;
        switch (operation.toLowerCase()){
            case ("morethan"):
                sock = sockRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case ("lessthan"):
                sock = sockRepository.findByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case ("equal"):
                sock = sockRepository.findByColorAndCottonPart(color, cottonPart);
                break;
            default: throw new BadArgumentException(operation);
        }
        return sock.getQuantity();
    }

    //добавить определенное количество пар носков.
    //Если комбинация цвет+процент не встречается, то создается новая.
    public String increaseSocksQuantityByParams(Sock sockIncome){
        Sock sock;
        sock = sockRepository.
                findByColorAndCottonPart(sockIncome.getColor(),sockIncome.getCottonPart());
        if (sock == null) {
            sockRepository.save(sockIncome);
            return "new";
        }
        sock.setQuantity(sock.getQuantity()+sockIncome.getQuantity());
        sockRepository.save(sock);
        return sock.getQuantity().toString();
    }

    public String decreaseSocksQuantityByParams(Sock sockOutcome) throws NoSuchSocksFoundException{
        Sock sock = sockRepository.
                findByColorAndCottonPart(sockOutcome.getColor(), sockOutcome.getCottonPart());
        if (sock == null){
            throw new NoSuchSocksFoundException();
        }
        Integer quantity = sock.getQuantity() - sockOutcome.getQuantity();
        if (quantity < 0){
            throw new NoSuchSocksFoundException(sock.getQuantity().toString());
        }
        sock.setQuantity(quantity);
        sockRepository.save(sock);
        return quantity.toString();
    }

    //тут идея была возвращать все что есть, если запрошено больше имеющегося, но решил без самодеятельности.
/*    public String decreaseSocksQuantityByParams(Sock sockOutcome){
        Sock sock;
        sock = sockRepository.
                findByColorAndCottonPart(sockOutcome.getColor(),sockOutcome.getCottonPart());
        if (sock == null) {
            return "error, there is no such combination in the database";
        }

        int quantity = sock.getQuantity();
        int newQuantity = quantity-sockOutcome.getQuantity();

        if (newQuantity<0) {
            sock.setQuantity(0);
            return "outcome only " + quantity + " socks";
        }
        return "updated: new quantity = " + sock.getQuantity();
    }*/
}
