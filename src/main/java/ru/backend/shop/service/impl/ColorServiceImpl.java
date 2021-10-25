package ru.backend.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.shop.entities.Color;
import ru.backend.shop.repository.ColorRepository;
import ru.backend.shop.service.ColorService;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public Color add(String colorName) {
        Color newColor = new Color(null, colorName);
        return colorRepository.save(newColor);
    }

    @Override
    public Color findColor(String colorName)  {
        Optional<Color> colorOptional = colorRepository.findColorByColorName(colorName);
        return colorOptional.orElse(null);
    }
}