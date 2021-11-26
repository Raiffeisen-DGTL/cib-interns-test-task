package ru.sillyseal.raiffeisentask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sillyseal.raiffeisentask.model.Sock;
import ru.sillyseal.raiffeisentask.model.SockId;
import ru.sillyseal.raiffeisentask.repository.SockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SockService {

    private final SockRepository sockRepository;

    @Autowired
    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public void incomeSocks(String color, int cotton_part, int quantity){
        SockId id = new SockId(color,cotton_part);
        if (sockRepository.existsById(id)) {
            Sock toUpdate = sockRepository.getById(id);
            toUpdate.setQuantity(toUpdate.getQuantity() + quantity);
            sockRepository.save(toUpdate);
        }
        else {
            sockRepository.save(new Sock(color,cotton_part,quantity));
        }
    }

    public void outcomeSocks(String color, int cotton_part, int quantity){
        SockId id = new SockId(color,cotton_part);
        if (sockRepository.existsById(id)) {
            Sock toUpdate = sockRepository.getById(id);
            if (quantity < toUpdate.getQuantity()) {
                toUpdate.setQuantity(toUpdate.getQuantity() - quantity);
                sockRepository.save(toUpdate);
            } else if (quantity == toUpdate.getQuantity()){
                sockRepository.delete(toUpdate);
            }
        }
    }

    public int countSocks(String color, String operation, int cotton_part){
        Optional<Integer> qty = Optional.empty();
        switch (operation.toLowerCase()){
            case "morethan": qty = sockRepository.countMoreThan(color,cotton_part);
                             break;
            case "equal": qty = sockRepository.countEqual(color,cotton_part);
                          break;
            case "lessthan": qty = sockRepository.countLessThan(color,cotton_part);
                             break;
        }
        return qty.isEmpty() ? 0 : qty.get();
    }

    public List<Sock> getSocks(){
        return sockRepository.findAll();
    }

}
