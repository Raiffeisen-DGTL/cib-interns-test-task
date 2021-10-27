package ru.raiffeisen.socks.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.socks.entity.Color;
import ru.raiffeisen.socks.exception.EmptyColorNameException;
import ru.raiffeisen.socks.repository.ColorRepository;

import javax.transaction.Transactional;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Transactional
    public void addColor(String color) {
        if (color.isEmpty()) {
            throw new EmptyColorNameException();
        }
        if (colorRepository.findByName(color).isEmpty()) {
            colorRepository.save(new Color(color));
        }
    }
}
