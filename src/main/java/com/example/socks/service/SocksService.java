package com.example.socks.service;

import com.example.socks.entity.Color;
import com.example.socks.entity.Sock;
import com.example.socks.exception.ColorDoesNotExistsException;
import com.example.socks.exception.NotEnoughSocksException;
import com.example.socks.exception.OperationNotFoundException;
import com.example.socks.exception.SocksException;
import com.example.socks.model.SockModel;
import com.example.socks.repository.ColorRepository;
import com.example.socks.repository.SockRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final ColorRepository colorRepository;
    private final SockRepository sockRepository;

    public int addSocks(@NotNull SockModel sockModel) {
        Color color = colorRepository.findColorByName(sockModel.getColor())
                .orElseThrow(ColorDoesNotExistsException::new);
        Sock sock = sockRepository
                .findByColorAndCottonPart(color, sockModel.getCottonPart())
                .orElse(new Sock(color, sockModel.getCottonPart(), 0));
        int newQuantity = sock.getQuantity() + sockModel.getQuantity();
        sock.setQuantity(newQuantity);

        sockRepository.save(sock);

        return newQuantity;
    }

    public int subSocks(@NotNull SockModel sockModel) {
        Color color = colorRepository
                .findColorByName(sockModel.getColor())
                .orElseThrow(ColorDoesNotExistsException::new);
        Sock sock = sockRepository
                .findByColorAndCottonPart(color, sockModel.getCottonPart())
                .orElseThrow(NotEnoughSocksException::new);
        if (sock.getQuantity() < sockModel.getQuantity())
            throw new NotEnoughSocksException();

        int newQuantity = sock.getQuantity() - sockModel.getQuantity();
        sock.setQuantity(newQuantity);

        if (newQuantity == 0)
            sockRepository.delete(sock);
        else
            sockRepository.save(sock);

        return newQuantity;
    }

    public int getSocks(String colorName, String operation, int cottonPart) {
        Color color = colorRepository
                .findColorByName(colorName)
                .orElseThrow(ColorDoesNotExistsException::new);

        int quantity = switch (operation) {
            case "moreThan" -> sockRepository
                    .findByColorAndCottonPartGreaterThan(color, cottonPart)
                    .stream()
                    .mapToInt(Sock::getQuantity)
                    .sum();
            case "lessThan" -> sockRepository
                    .findByColorAndCottonPartLessThan(color, cottonPart)
                    .stream()
                    .mapToInt(Sock::getQuantity)
                    .sum();
            case "equal" -> sockRepository
                    .findByColorNameAndCottonPart(colorName, cottonPart)
                    .orElse(new Sock(null, 0, 0))
                    .getQuantity();
            default -> throw new OperationNotFoundException("Operation " + operation + " not found");
        };

        return quantity;
    }
}
