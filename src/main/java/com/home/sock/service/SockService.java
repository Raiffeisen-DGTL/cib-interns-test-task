package com.home.sock.service;

import com.home.sock.dto.SocksIncomeOutcomeDto;
import com.home.sock.repository.SockRepository;
import com.home.sock.models.Color;
import com.home.sock.models.Composite;
import com.home.sock.models.Sock;
import com.home.sock.repository.ColorRepository;
import com.home.sock.repository.CompositeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SockService {
    @Autowired
    SockRepository sockRepository;
    @Autowired
    CompositeRepository compositeRepository;
    @Autowired
    ColorRepository colorRepository;

    public Color findColorOrSave(String color) {
        color = color.trim();
        Optional<Color> colorToDb = this.colorRepository.findOneByColor(color);
        if (colorToDb.isEmpty())
            return this.colorRepository.save(new Color(color));
        else return colorToDb.get();
    }

    public Composite findCottonPartOrSave(int cottonPart) {
        Optional<Composite> cpToDb = this.compositeRepository.findOneByCottonPart(cottonPart);
        if (cpToDb.isEmpty())
            return this.compositeRepository.save(new Composite(cottonPart));
        else return cpToDb.get();
    }

    public boolean income(SocksIncomeOutcomeDto dto) {
        Optional<Sock> Sock;
        Optional<Color> color = this.colorRepository.findOneByColor(dto.getColor());
        Optional<Composite> composite = this.compositeRepository.findOneByCottonPart(dto.getCottonPart());
        if (color.isPresent() && composite.isPresent()) {
            Sock = this.sockRepository.findOneByColorAndComposite(color.get(), composite.get());
        } else {
            saveNewPosition(dto);
            return true;
        }
        if (Sock.isPresent()) {
            Sock.get().incomeSocks(dto.getQuantity());
            this.sockRepository.save(Sock.get());
        } else {
            this.sockRepository.save(new Sock(color.get(), dto.getQuantity(), composite.get()));
        }
        return true;
    }

    public void saveNewPosition(SocksIncomeOutcomeDto dto) {
        Color color = findColorOrSave(dto.getColor());
        Composite composite = findCottonPartOrSave(dto.getCottonPart());
        Sock Sock = new Sock(color, dto.getQuantity(), composite);
        this.sockRepository.save(Sock);
    }

    public boolean outcome(SocksIncomeOutcomeDto dto) {
        Optional<Sock> Sock;
        Optional<Color> color = this.colorRepository.findOneByColor(dto.getColor());
        Optional<Composite> composite = this.compositeRepository.findOneByCottonPart(dto.getCottonPart());
        if (color.isPresent() && composite.isPresent()) {
            Sock = this.sockRepository.findOneByColorAndComposite(color.get(), composite.get());
        } else {
            return false;
        }
        if (Sock.isPresent() && Sock.get().getQuantity() >= dto.getQuantity()) {
            Sock.get().outcomeSocks(dto.getQuantity());
            this.sockRepository.save(Sock.get());
        } else {
            return false;
        }
        return true;
    }
}
