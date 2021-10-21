package com.example.demo.service;

import com.example.demo.domain.Socks;
import com.example.demo.dto.SocksDto;
import com.example.demo.exceptions.IncorrectParametersException;
import com.example.demo.exceptions.NoSuchSocksException;
import com.example.demo.repository.SocksRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Validated
public class SocksService implements ISocksService {

    public final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    @Transactional
    public @NonNull Long getByColorAndCottonPart(@NonNull String color,
                                                 @NonNull String operation,
                                                 @NonNull Long cottonPart) {
        Socks socks;
        switch (operation) {
            case "moreThan":
                socks = socksRepository.getSocksByColorAndCottonPartIsGreaterThan(color, cottonPart)
                        .orElseThrow(() -> new NoSuchSocksException("No socks with such parameters"));
                return socks.getQuantity();
            case "lessThan":
                socks = socksRepository.getSocksByColorAndCottonPartLessThan(color, cottonPart)
                        .orElseThrow(() -> new NoSuchSocksException("No socks with such parameters"));
                return socks.getQuantity();
            case "equals":
                socks = socksRepository.getSocksByColorAndCottonPartEquals(color, cottonPart)
                        .orElseThrow(() -> new NoSuchSocksException("No socks with such parameters"));
                return socks.getQuantity();
            default:
                throw new IncorrectParametersException("Incorrect parameters");
        }
    }

    @Override
    @Transactional
    public void add(@NonNull SocksDto socksDto) {
        Optional<Socks> socks = socksRepository.getSocksByColorAndCottonPartEquals
                (socksDto.getColor(), socksDto.getCottonPart());
        if (!socks.isPresent()) {
            socksRepository.save(new Socks(socksDto));
        } else {
            Socks socks1 = socks.get();
            socks1.setQuantity(socksDto.getQuantity() + socks1.getQuantity());
            socksRepository.save(socks1);
        }
    }

    @Override
    @Transactional
    public void reduce(@NonNull SocksDto socksDto) {
        Socks socks = socksRepository.getSocksByColorAndCottonPartEquals(socksDto.getColor(), socksDto.getCottonPart())
                .orElseThrow(() -> new NoSuchSocksException("No socks with such parameters"));
        socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
        socksRepository.save(socks);
    }
}
