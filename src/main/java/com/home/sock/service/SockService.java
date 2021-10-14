package com.home.sock.service;

import com.home.sock.dto.SocksIncomeOutcomeDto;
import com.home.sock.repository.SockRepository;
import com.home.sock.models.Color;
import com.home.sock.models.CottonPart;
import com.home.sock.models.Sock;
import com.home.sock.repository.ColorRepository;
import com.home.sock.repository.CottonPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SockService {
    @Autowired
    SockRepository sockRepository;
    @Autowired
    CottonPartRepository cottonPartRepository;
    @Autowired
    ColorRepository colorRepository;

    public Color findColorOrSave(String color) {
        color = color.trim();
        Optional<Color> colorToDb = this.colorRepository.findOneByColor(color);
        if (colorToDb.isEmpty())
            return this.colorRepository.save(new Color(color));
        else return colorToDb.get();
    }

    public CottonPart findCottonPartOrSave(int cottonPart) {
        Optional<CottonPart> cpToDb = this.cottonPartRepository.findOneByCottonPart(cottonPart);
        if (cpToDb.isEmpty())
            return this.cottonPartRepository.save(new CottonPart(cottonPart));
        else return cpToDb.get();
    }

    public boolean income(SocksIncomeOutcomeDto dto) {
        Optional<Sock> Sock;
        Optional<Color> color = this.colorRepository.findOneByColor(dto.getColor());
        Optional<CottonPart> cottonPart = this.cottonPartRepository.findOneByCottonPart(dto.getCottonPart());
        if (color.isPresent() && cottonPart.isPresent()) {
            Sock = this.sockRepository.findOneByColorAndCottonPart(color.get(), cottonPart.get());
        } else {
            saveNewPosition(dto);
            return true;
        }
        if (Sock.isPresent()) {
            Sock.get().incomeSocks(dto.getQuantity());
            this.sockRepository.save(Sock.get());
        } else {
            this.sockRepository.save(new Sock(color.get(), dto.getQuantity(), cottonPart.get()));
        }
        return true;
    }

    public void saveNewPosition(SocksIncomeOutcomeDto dto) {
        Color color = findColorOrSave(dto.getColor());
        CottonPart cottonPart = findCottonPartOrSave(dto.getCottonPart());
        Sock Sock = new Sock(color, dto.getQuantity(), cottonPart);
        this.sockRepository.save(Sock);
    }

    public boolean outcome(SocksIncomeOutcomeDto dto) {
        Optional<Sock> Sock;
        Optional<Color> color = this.colorRepository.findOneByColor(dto.getColor());
        Optional<CottonPart> cottonPart = this.cottonPartRepository.findOneByCottonPart(dto.getCottonPart());
        if (color.isPresent() && cottonPart.isPresent()) {
            Sock = this.sockRepository.findOneByColorAndCottonPart(color.get(), cottonPart.get());
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
