package com.example.socksstr.Service;

import com.example.socksstr.Model.BaseEntity;
import com.example.socksstr.Model.Socks;
import com.example.socksstr.Repository.SocksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    SocksRepository socksRepository;

    public void socksIncome(Socks socks) {
        BaseEntity id = new BaseEntity(socks.getColor(), socks.getCottonPart());
        Optional<Socks> socksFind = socksRepository.findById(id);

        if (socksFind.isPresent()) {
            Socks socksFound = socksFind.get();
            socksFound.setQuantity(socks.getQuantity() + socksFound.getQuantity());
            socksRepository.save(socksFound);
        } else socksRepository.save(socks);
    }


    public void socksOutcome(Socks socks) {
        BaseEntity id = new BaseEntity(socks.getColor(), socks.getCottonPart());
        Optional<Socks> socksFind = socksRepository.findById(id);

        if (socksFind.isPresent()) {
            Socks socksFound = socksFind.get();

            if (socks.getQuantity() < socksFound.getQuantity()) {
                socksFound.setQuantity(socksFound.getQuantity() - socks.getQuantity());
                socksRepository.save(socksFound);
            }
        } else
            throw new EntityNotFoundException(String.format(
                    "Not found '%s' socks with '%d' cottonPart in storage.",
                    socks.getColor(), socks.getCottonPart()));
    }

    public long getSocksQuantity(String color, String operation, long cottonPart) {
        Optional<Integer> result = Optional.empty();

        switch (operation.toLowerCase()) {
            case ("lessthan"):
                result = socksRepository.sumQuantityByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case ("morethan"):
                result = socksRepository.sumQuantityByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case ("equal"):
                result = socksRepository.sumQuantityByColorAndCottonPartEquals(color, cottonPart);
                break;
        }

        return result.orElse(0);
    }
}
