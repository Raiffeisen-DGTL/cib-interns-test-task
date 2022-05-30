package com.lazarev.socksapi.service;

import com.lazarev.socksapi.dto.SockDto;
import com.lazarev.socksapi.entity.CottonPart;
import com.lazarev.socksapi.entity.Sock;
import com.lazarev.socksapi.entity.SockColor;
import com.lazarev.socksapi.exception.NotEnoughSocksOnStorehouseException;
import com.lazarev.socksapi.exception.OperationNotFoundException;
import com.lazarev.socksapi.exception.SockNotFoundException;
import com.lazarev.socksapi.repository.CottonPartRepository;
import com.lazarev.socksapi.repository.SockColorRepository;
import com.lazarev.socksapi.repository.SockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SockServiceImpl implements SockService {

    private final SockRepository sockRepository;
    private final SockColorRepository colorRepository;
    private final CottonPartRepository cottonPartRepository;

    @Override
    public Sock addSocks(SockDto dto) {
        CottonPart cottonPart = cottonPartRepository
                .findByCottonPart(dto.getCottonPart())
                .orElseGet(() -> cottonPartRepository
                        .save(new CottonPart().setCottonPart(dto.getCottonPart())));

        SockColor sockColor = colorRepository
                .findByColor(dto.getColor().toLowerCase())
                .orElseGet(() -> colorRepository
                        .save(new SockColor().setColor(dto.getColor().toLowerCase())));


        Optional<Sock> sockOptional = sockRepository
                .findBySockColorAndCottonPart(sockColor, cottonPart);

        Sock sock;
        if(sockOptional.isPresent()){
            sock = sockOptional.get();
            sock.setQuantity(sock.getQuantity() + dto.getQuantity());
        }
        else {
            sock =  new Sock()
                    .setSockColor(sockColor)
                    .setCottonPart(cottonPart)
                    .setQuantity(dto.getQuantity());
        }

        return sockRepository.save(sock);
    }

    @Override
    public Sock subSocks(SockDto dto) {
        CottonPart cottonPart = cottonPartRepository
                .findByCottonPart(dto.getCottonPart())
                .orElseGet(() -> {
                    throw new SockNotFoundException(String.format(
                            "Sock with cotton_part = '%d' not found", dto.getCottonPart()));
                });

        SockColor sockColor = colorRepository
                .findByColor(dto.getColor().toLowerCase())
                .orElseGet(() -> {
                    throw new SockNotFoundException(
                            String.format("Sock with color = '%s' not found", dto.getColor()));
                });


        Optional<Sock> sockOptional = sockRepository
                .findBySockColorAndCottonPart(sockColor, cottonPart);

        if(sockOptional.isPresent()){
            Sock sock = sockOptional.get();
            if(sock.getQuantity() >= dto.getQuantity()){
                sock.setQuantity(sock.getQuantity() - dto.getQuantity());
                return sockRepository.save(sock);
            }
            else {
                throw new NotEnoughSocksOnStorehouseException(String.format(
                        "Not enough socks on storehouse: required=%d, in stock=%d",
                        dto.getQuantity(), sock.getQuantity()));
            }
        }
        else {
            throw new SockNotFoundException(String.format(
                    "Sock with color = '%s' and cotton_part = '%d' not found",
                    dto.getColor(), dto.getCottonPart()));
        }
    }

    @Override
    public Integer countRequestMatchingSocks(String color, String operation, Integer cottonPart) {

        switch (operation){
            case "lessThan":
                return sockRepository.sumBySockColorAndCottonPartLessThan(color, cottonPart);
            case "moreThan":
                return sockRepository.sumBySockColorAndCottonPartMoreThan(color, cottonPart);
            case "equal":
                return sockRepository.sumBySockColorAndCottonPartEquals(color, cottonPart);
            default:
                throw new OperationNotFoundException(String.format(
                        "Operation '%s' is not allowed. " +
                        "You can use only the following: 'lessThan', 'moreThan', 'equal'", operation));
        }
    }
}
