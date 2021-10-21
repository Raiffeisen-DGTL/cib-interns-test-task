package com.leo.socks.service;

import com.leo.socks.dto.GettingByParamsDto;
import com.leo.socks.dto.IncomeOutcomeDto;
import com.leo.socks.entity.SocksEntity;
import com.leo.socks.exception.NoSuchSocksException;
import com.leo.socks.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SocksService {

    private final SocksRepository socksRepository;

    public SocksEntity getSocks(IncomeOutcomeDto socks) {
        return socksRepository.getByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
    }

    public void save(IncomeOutcomeDto socks) {
        socksRepository.save(
                new SocksEntity(
                        0,
                        socks.getColor(),
                        socks.getCottonPart(),
                        socks.getQuantity()
                )
        );
    }

    public void add(IncomeOutcomeDto socks) {
        SocksEntity currentSocks = getSocks(socks);

        if(currentSocks == null) {
            save(socks);
        }
        else {
            currentSocks.setQuantity(currentSocks.getQuantity() + socks.getQuantity());
            socksRepository.save(currentSocks);
        }
    }

    public void take(IncomeOutcomeDto socks) {
        SocksEntity currentSocks = getSocks(socks);

        if (currentSocks == null || currentSocks.getQuantity() - socks.getQuantity() < 0) {
            throw new NoSuchSocksException();
        }
        if (currentSocks.getQuantity() - socks.getQuantity() == 0) {
            socksRepository.delete(currentSocks);
        }
        else {
            currentSocks.setQuantity(currentSocks.getQuantity() - socks.getQuantity());
            socksRepository.save(currentSocks);
        }
    }

    public int getQuantity(GettingByParamsDto params) {
        List<SocksEntity> socks = new ArrayList<>();
        int countOfSocks = 0;

        switch(params.getOperation()) {
            case moreThan:
                socks = socksRepository.
                        findSocksEntitiesByColorAndCottonPartIsGreaterThan(params.getColor(), params.getCottonPart());
                break;
            case equal:
                socks = socksRepository.
                        findSocksEntitiesByColorAndCottonPartEquals(params.getColor(), params.getCottonPart());
                break;
            case lessThan:
                socks = socksRepository.
                        findSocksEntitiesByColorAndCottonPartIsLessThan(params.getColor(), params.getCottonPart());
                break;
        }

        for (SocksEntity sock: socks) {
            countOfSocks += sock.getQuantity();
        }

        return countOfSocks;
    }
}
