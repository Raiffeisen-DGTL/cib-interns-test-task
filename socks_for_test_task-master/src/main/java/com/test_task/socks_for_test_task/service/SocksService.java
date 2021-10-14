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

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void income(@NonNull Socks socks) {
        if (socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100 && socks.getQuantity() > 0){
            Optional<Socks> socksOptional = socksRepository
                    .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
            if (socksOptional.isPresent()) {
                Socks findSocks = socksOptional.get();
                findSocks.setQuantity(findSocks.getQuantity() + socks.getQuantity());
            } else socksRepository.save(socks);
        }
        else throw new ApiInvalidParameterException("Request parameters have an incorrect format!");
    }

    public void outcome(@NonNull Socks socks) {
        if (socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100 && socks.getQuantity() > 0){
            Optional<Socks> socksOptional = socksRepository
                    .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
            if (socksOptional.isPresent()) {
                Socks findSocks = socksOptional.get();
                if (findSocks.getQuantity() >= socks.getQuantity()) {
                    findSocks.setQuantity(findSocks.getQuantity() - socks.getQuantity());
                } else throw new ApiInvalidParameterException("Not enough socks!");
            }
            else throw new ApiInvalidParameterException("No socks of the specified parameters!");
        }
        else throw new ApiInvalidParameterException("Request parameters have an incorrect format!");
    }

    public int countOfSocks(@NonNull String color, @NonNull String operation, int cottonPart){
        Optional<Socks> optionalSocks;
        if (cottonPart >= 0 && cottonPart <= 100){
            switch (operation) {
                case "moreThan":
                    optionalSocks = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                case "lessThan":
                    optionalSocks = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                case "equal":
                    optionalSocks = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                default:
                    throw new ApiInvalidParameterException("Operation name has an incorrect format!");
            }
            throw new ApiInvalidParameterException("No socks of the specified parameters!");
        }
        throw new ApiInvalidParameterException("Parameters don't exist!");
    }





}
