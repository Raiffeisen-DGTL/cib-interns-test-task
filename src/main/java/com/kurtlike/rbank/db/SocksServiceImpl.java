package com.kurtlike.rbank.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Repository
public class SocksServiceImpl implements SocksService {
    @Autowired
    private  SocksRepository socksRepository;

    @Override
    public long addSocks(String color, int cottonPart, int quantity) {
        Optional<Socks> socks = socksRepository.findFirstByColorAndCottonpart(color,cottonPart);
        if(socks.isPresent()){
            int q = socks.get().getQuantity();
            socks.get().setQuantity(q + quantity);
            socksRepository.save(socks.get());
        }else {
           Socks newSocks = new Socks();
           newSocks.setColor(color);
           newSocks.setCottonpart(cottonPart);
           newSocks.setQuantity(quantity);
           socksRepository.save(newSocks);
        }
        return 0;
    }

    @Override
    public long getSocks(String color, int cottonPart, int quantity) {
        Optional<Socks> socks = socksRepository.findFirstByColorAndCottonpart(color,cottonPart);
        if(socks.isPresent()){
            int q = socks.get().getQuantity();
            if(q >= quantity) {
                socks.get().setQuantity(q - quantity);
                socksRepository.save(socks.get());
                return 0;
            }
        }
        return -1;
    }

    @Override
    public long getQuantity(String color, String operation, int cottonPart) {
        Optional<Socks> o = Optional.empty();
        switch (operation){
            case "moreThan":{
                o = socksRepository.findFirstByColorAndCottonpartGreaterThan(color,cottonPart);
                break;
            }
            case "lessThan":{
                o = socksRepository.findFirstByColorAndCottonpartLessThan(color,cottonPart);
                break;
            }
            case "equal":{
                o = socksRepository.findFirstByColorAndCottonpart(color,cottonPart);
                break;
            }
        }
        return o.map(Socks::getQuantity).orElse(-1);
    }
}
