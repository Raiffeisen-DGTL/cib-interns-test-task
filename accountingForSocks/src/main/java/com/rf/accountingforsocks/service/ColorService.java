package com.rf.accountingforsocks.service;

import com.rf.accountingforsocks.entity.Color;
import com.rf.accountingforsocks.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    @Autowired
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Color findColorByName(String name) {
        return colorRepository.findColorByName(name).orElseThrow();
    }
}
