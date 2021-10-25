package ru.project.raiffeisenbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.raiffeisenbank.dto.IncomeRequest;
import ru.project.raiffeisenbank.dto.IncomeResponse;
import ru.project.raiffeisenbank.dto.OutcomeRequest;
import ru.project.raiffeisenbank.dto.OutcomeResponse;
import ru.project.raiffeisenbank.entity.Socks;
import ru.project.raiffeisenbank.exception.SocksNotFoundException;
import ru.project.raiffeisenbank.mapper.SocksMapper;
import ru.project.raiffeisenbank.repos.SocksRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository repository;

    private final SocksMapper mapper;

    @Autowired
    public SocksService(SocksRepository repository, SocksMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public IncomeResponse income(IncomeRequest request) {
        String color = request.getColor();
        int cottonPart = request.getCottonPart();
        Long quantity = request.getQuantity();

        SocksValidation socksValidation = new SocksValidation();
        socksValidation.validateIncomeOutcomeException(color, cottonPart, quantity);

        Optional<Socks> socksOptional = repository.getSocksByColorAndCottonPart(color, cottonPart);

        if (socksOptional.isPresent()) {
            socksOptional.get().setQuantity(socksOptional.get().getQuantity() + quantity);
            return mapper.toIncomeResponse(repository.save(socksOptional.get()));
        } else {
            Socks socks = new Socks(color, cottonPart, quantity);
            return mapper.toIncomeResponse(repository.save(socks));
        }
    }

    @Transactional
    public OutcomeResponse outcome(OutcomeRequest request) {
        String color = request.getColor();
        int cottonPart = request.getCottonPart();
        Long quantity = request.getQuantity();

        SocksValidation socksValidation = new SocksValidation();
        socksValidation.validateIncomeOutcomeException(color, cottonPart, quantity);

        Optional<Socks> socks = repository.getSocksByColorAndCottonPart(color, cottonPart);

        if (socks.isPresent()) {
            socks.get().setQuantity(socks.get().getQuantity() - quantity);
            return mapper.toOutcomeResponse(repository.save(socks.get()));
        } else {
            throw new SocksNotFoundException("Socks with color " + color + " and cotton part " + cottonPart + " not found!");
        }
    }

    @Transactional
    public Long getSocks(String color, String operation, int cottonPart) {
        SocksValidation socksValidation = new SocksValidation();
        socksValidation.validateGetSocksException(color, operation, cottonPart);

        return switch (operation) {
            case "moreThan" -> repository.getSocksMoreThan(color, cottonPart);
            case "lessThan" -> repository.getSocksLessThan(color, cottonPart);
            case "equal" -> repository.getSocksEqual(color, cottonPart);
            default -> null;
        };
    }
}
