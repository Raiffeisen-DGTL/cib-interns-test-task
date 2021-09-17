package com.example.sock.service;

import com.example.sock.domain.Socks;
import com.example.sock.repos.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksService implements ISocksService{
    private final SocksRepository socksRepository;
    @Override
    public Optional<Socks> getByColorAndCottonPart(String color, String operation, int cottonPart) {
        switch (operation){
            case "moreThan": return socksRepository.getSocksByColorAndCottonPartIsGreaterThan(color, cottonPart);
            case "lessThan": return socksRepository.getSocksByColorAndCottonPartLessThan(color, cottonPart);
            case "equals": return socksRepository.getSocksByColorAndCottonPartEquals(color, cottonPart);
            default:
                return Optional.empty();

        }
    }

    @Override
    public void addSocks(Socks socks) {
        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isEmpty()){
            socksRepository.save(socks);
        }else{
            Socks socksInBD = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(socks.getQuantity() + socksInBD.getQuantity());
            socksRepository.save(socksInBD);
        }
    }
    @Override
    public void reduceSocks(Socks socks) {
        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).isPresent()) {
            Socks socksInBD = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).get();
            socksInBD.setQuantity(Math.max(socks.getQuantity() - socksInBD.getQuantity(), 0));
            socksRepository.save(socksInBD);
        }
    }
}
