package ru.raiffeisen.socksforraif.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raiffeisen.socksforraif.dao.SocksJson;
import ru.raiffeisen.socksforraif.exceptions.BadRequestException;
import ru.raiffeisen.socksforraif.models.Socks;
import ru.raiffeisen.socksforraif.repositories.SocksRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SocksService {


    @Autowired
    private SocksRepo socksRepo;


    public Optional<Boolean> isCorrectOperation(String operation) {
        if ( Arrays.stream(SocksServiceOperations.values()).anyMatch(ops -> ops.name().equals(operation))){return Optional.of(true);}
        return Optional.empty();
    }

    public Optional<Boolean> isCorrectCottonPart(String cottonPart) {
        if (Byte.parseByte(cottonPart) >= 0 && Byte.parseByte(cottonPart) <= 100){return Optional.of(true);}
        return Optional.empty();
    }

    public Integer executeQuery(String color, String operation, String cottonPart) throws BadRequestException {
        isCorrectOperation(operation).orElseThrow(() -> new BadRequestException("incorrect operation type"));
        isCorrectCottonPart(cottonPart).orElseThrow(() -> new BadRequestException("incorrect cotton percentage"));
        SocksServiceOperations operationToDo = SocksServiceOperations.valueOf(operation);
        Optional<List<Socks>> socks = Optional.empty();
        switch (operationToDo) {
            case moreThan:
                socks = Optional.of(socksRepo.findAllByColorAndCottonPartAfter(color, Byte.valueOf(cottonPart)));
                break;
            case lessThan:
                socks = Optional.of(socksRepo.findAllByColorAndCottonPartBefore(color, Byte.valueOf(cottonPart)));
                break;
            case equal:
                socks = Optional.of(socksRepo.findAllByColorAndCottonPart(color, Byte.valueOf(cottonPart)));
                break;
        }
        return socks.orElseThrow(() -> new BadRequestException("incorrect cotton procentage")).stream().mapToInt(elem -> elem.getQuantity()).sum();
    }

    public void socksIncome(SocksJson socksJson) {
        jsonIsValid(socksJson).orElseThrow(() -> new BadRequestException("json file error, absence of property(ies)"));
        Optional<Socks> socks = socksRepo.findByColorAndCottonPart(socksJson.getColor(), socksJson.getCottonPart());
        socks.ifPresent(raifSocks -> socksRepo.save(raifSocks.setQuantity(raifSocks.getQuantity() + socksJson.getQuantity())));
    }

    public void socksOutcome(SocksJson socksJson) {
        jsonIsValid(socksJson).orElseThrow(() -> new BadRequestException("json file error, absence of property(ies)"));
        Socks socks = isSocksAvailable(socksJson);
        isSocksOutPossible(socks, socksJson).orElseThrow(() -> new BadRequestException("there is not enough socks in stock for that operation"));
        socksRepo.save(socks.setQuantity(socks.getQuantity() - socksJson.getQuantity()));
    }

    public Optional<Boolean> isSocksOutPossible(Socks socks, SocksJson json) {
        if (socks.getQuantity() >= json.getQuantity()){return Optional.of(true);}
        return Optional.empty();
    }


    private Optional<Boolean> jsonIsValid(SocksJson socksJson) {
        if (socksJson.getCottonPart() == null || socksJson.getColor() == null || socksJson.getQuantity() == null){throw new BadRequestException("data were provided incorrectly");}
        if (socksJson.getQuantity() >= 1 && socksJson.getCottonPart() >= 0 && socksJson.getCottonPart() <= 100){return Optional.of(true);}
        return Optional.empty();
    }

    public Socks isSocksAvailable(SocksJson socksJson) {
        return socksRepo.findByColorAndCottonPart(socksJson.getColor(), socksJson.getCottonPart()).orElseThrow(() -> new BadRequestException("socks not found"));
    }
}
