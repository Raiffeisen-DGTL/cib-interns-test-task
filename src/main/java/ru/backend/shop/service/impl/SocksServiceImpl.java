package ru.backend.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.shop.entities.Color;
import ru.backend.shop.entities.Socks;
import ru.backend.shop.entities.dto.SocksDto;
import ru.backend.shop.repository.SocksRepository;
import ru.backend.shop.service.ColorService;
import ru.backend.shop.service.SocksService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    SocksRepository socksRepository;

    @Autowired
    ColorService colorService;

    @Override
    public void addSocks(SocksDto socksDto) {
        String colorName = socksDto.getColor();
        Color colorClass = colorService.findColor(colorName);

        Socks socks = new Socks(null, socksDto.getCottonPart(), socksDto.getQuantity(), colorClass);
        if(colorClass == null) {
            saveNewSocks(socks, colorName);
        } else {
            updateSocksWhenAdding(socks, colorClass);
        }
    }

    @Override
    public void deleteSocks(SocksDto socksDto) {
        String colorName = socksDto.getColor();
        Color colorClass = colorService.findColor(colorName);

        Socks socks = new Socks(null, socksDto.getCottonPart(), socksDto.getQuantity(), colorClass);
        if(colorClass != null) {
            updateSocksWhenDeleted(socks, colorClass);
        }
    }

    @Override
    public List<SocksDto> getSocksByParameters(String colorName, String operation, Integer cottonPart) {
        Color color = colorService.findColor(colorName);
        List<Socks> socks = getSocksByOperation(operation, color, cottonPart);
        return convertSocksToSocksDto(socks);
    }

    private void saveNewSocks(Socks socks, String colorName) {
        Color colorClass = colorService.add(colorName);
        socks.setColor(colorClass);
        socksRepository.save(socks);
    }

    private void updateSocksWhenAdding(Socks socks, Color colorClass) {
        Socks oldSocks = getSockByColorAndCottonPart(colorClass, socks.getCottonPart());
        if(oldSocks == null) {
            socks.setColor(colorClass);
            socksRepository.save(socks);
        } else {
            int quantity = oldSocks.getQuantity() + socks.getQuantity();
            oldSocks.setQuantity(quantity);
            socksRepository.save(oldSocks);
        }
    }

    private void updateSocksWhenDeleted(Socks socks, Color colorClass) {
        Socks oldSocks = getSockByColorAndCottonPart(colorClass, socks.getCottonPart());
        if(oldSocks != null) {
            if (oldSocks.getQuantity() > socks.getQuantity()) {
                int quantity = oldSocks.getQuantity() - socks.getQuantity();
                oldSocks.setQuantity(quantity);
                socksRepository.save(oldSocks);
            } else {
                socksRepository.delete(oldSocks);
            }
        }
    }

    private Socks getSockByColorAndCottonPart(Color colorClass, Integer cottonPart) {
        Optional<Socks> socksOptional = socksRepository.findByColorAndCottonPart(colorClass, cottonPart);
        return socksOptional.orElse(null);
    }

    private List<Socks> getSocksByOperation(String operation, Color color, Integer cottonPart) {
        switch (operation) {
            case "moreThan":
                return socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
            case "equal":
                return socksRepository.findSocksByColorAndCottonPart(color, cottonPart);
            case "lessThan":
                return socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
            default:
                return Collections.emptyList();
        }
    }

    private List<SocksDto> convertSocksToSocksDto(List<Socks> socksList) {
        List<SocksDto> socksDto = new ArrayList<>();
        socksList.forEach(socks -> {
            SocksDto dto = new SocksDto(socks.getColor().getColorName(), socks.getCottonPart(), socks.getQuantity());
            socksDto.add(dto);
        });
        return socksDto;
    }
}