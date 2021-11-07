package com.test_task.socks_for_test_task.service;

import com.test_task.socks_for_test_task.exception.ApiInvalidParameterException;
import com.test_task.socks_for_test_task.model.Socks;
import com.test_task.socks_for_test_task.repository.SocksRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SocksService {

    private final SocksRepository socksRepository;

    private static final String EMPTY_SOCKS_WITH_PARAM = "No socks of the specified parameters!";

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void income(@NonNull Socks socks) {
        Optional<Socks> socksOptional = socksRepository
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksOptional.isPresent()) {
            Socks findSocks = socksOptional.get();
            findSocks.setQuantity(findSocks.getQuantity() + socks.getQuantity());
        }
        else {
            socksRepository.save(socks);
        }
    }

    public void outcome(@NonNull Socks socks) {
        Socks findSocks = socksRepository
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()).orElseThrow(()
                        -> new ApiInvalidParameterException("No socks of the specified parameters!"));
        if (findSocks.getQuantity() >= socks.getQuantity()) {
            findSocks.setQuantity(findSocks.getQuantity() - socks.getQuantity());
        }
        else {
            throw new ApiInvalidParameterException("Not enough socks!");
        }
    }

    public int countOfSocks(@NonNull String color, String operation, int cottonPart){
        switch (operation) {
            case "moreThan":
                return socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart)
                        .map(Socks::getQuantity)
                        .orElseThrow(() -> new ApiInvalidParameterException(EMPTY_SOCKS_WITH_PARAM));
            case "lessThan":
                return socksRepository.findByColorAndCottonPartLessThan(color, cottonPart)
                        .map(Socks::getQuantity)
                        .orElseThrow(() -> new ApiInvalidParameterException(EMPTY_SOCKS_WITH_PARAM));
            case "equal":
                return socksRepository.findByColorAndCottonPartEquals(color, cottonPart)
                        .map(Socks::getQuantity)
                        .orElseThrow(() -> new ApiInvalidParameterException(EMPTY_SOCKS_WITH_PARAM));
            default:
                throw new ApiInvalidParameterException("Operation name has an incorrect format!");
        }
    }

}
