package me.mlnn.raif.service.impl;

import lombok.RequiredArgsConstructor;
import me.mlnn.raif.entity.SocksEntity;
import me.mlnn.raif.exception.InvalidDataException;
import me.mlnn.raif.exception.NotEnoughSocksException;
import me.mlnn.raif.exception.SocksNotFoundException;
import me.mlnn.raif.model.SocksModel;
import me.mlnn.raif.repository.SocksRepository;
import me.mlnn.raif.service.SocksService;
import me.mlnn.raif.util.SocksValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    @Autowired
    private final SocksRepository repo;
    
    @Override
    @Transactional
    public void addSocks(SocksModel socks) {
        if (!SocksValidatorUtil.validateSocksModel(socks)) {
            throw new InvalidDataException();
        }
        
        SocksEntity socksInWarehouse = repo.findAllByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksInWarehouse != null) {
            socksInWarehouse.setQuantity(socksInWarehouse.getQuantity() + socks.getQuantity());
            repo.save(socksInWarehouse);
        } else {
            repo.save(new SocksEntity(socks));
        }
    }
    
    @Override
    @Transactional
    public void removeSocks(SocksModel socks) {
        if (!SocksValidatorUtil.validateSocksModel(socks)) {
            throw new InvalidDataException();
        }
        
        SocksEntity socksInWarehouse = repo.findAllByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksInWarehouse == null) {
            throw new SocksNotFoundException();
        }
        
        if (socksInWarehouse.getQuantity() < socks.getQuantity()) {
            throw new NotEnoughSocksException();
        }
        
        socksInWarehouse.setQuantity(socksInWarehouse.getQuantity() - socks.getQuantity());
        if (socksInWarehouse.getQuantity() == 0) {
            repo.delete(socksInWarehouse);
        } else {
            repo.save(socksInWarehouse);
        }
    }
    
    @Override
    public Integer getNumberOfSocks(String color, String operation, Integer cottonPart) {
        if (!SocksValidatorUtil.validateColor(color) && !SocksValidatorUtil.validateCottonPart(cottonPart)) {
            throw new InvalidDataException();
        }
        
        Integer result = switch (operation) {
            case "moreThan" -> repo.sumOfSocksByColorAndCottonPartMoreThan(color, cottonPart);
            case "lessThan" -> repo.sumOfSocksByColorAndCottonPartLessThan(color, cottonPart);
            case "equal" -> repo.sumOfSocksByColorAndCottonPartEqual(color, cottonPart);
            default -> throw new InvalidDataException();
        };
        
        return Objects.requireNonNullElse(result, 0);
    }
}
