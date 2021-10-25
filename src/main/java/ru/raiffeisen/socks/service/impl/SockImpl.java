package ru.raiffeisen.socks.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiffeisen.socks.database.entity.Sock;
import ru.raiffeisen.socks.database.repository.SockRepository;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.UnsupportedSocksOperationException;
import ru.raiffeisen.socks.service.SockService;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class SockImpl implements SockService {

    private final SockRepository sockRepository;

    @Override
    public int getQuantityOfSocks(String color, Operation operation, int cottonPart) {
        int quantity;
        switch (operation) {
            case MORE_THAN:
                quantity = sockRepository.findQuantitySumByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case LESS_THAN:
                quantity = sockRepository.findQuantitySumByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case EQUALS:
                quantity = sockRepository.findQuantitySumByColorAndCottonPartEquals(color, cottonPart);
                break;
            default:
                throw new UnsupportedSocksOperationException();
        }
        return quantity;
    }

    @Override
    @Transactional
    public void registerArrivalOfSocks(String color, int cottonPart, int quantity) {
        sockRepository.findByColorAndCottonPart(color, cottonPart).ifPresentOrElse(
                sock -> {
                    sock.setQuantity(sock.getQuantity() + quantity);
                    sockRepository.save(sock);
                }
                ,
                () -> sockRepository.save(new Sock(color, cottonPart, quantity))
        );
    }

    @Override
    @Transactional
    public void registerReleaseOfSocks(String color, int cottonPart, int quantity) {
        Sock sock = sockRepository.findByColorAndCottonPart(color, cottonPart)
                .orElseThrow(NotEnoughSocksException::new);

        if (sock.getQuantity() < quantity) {
            throw new NotEnoughSocksException();
        }
        if (sock.getQuantity() == quantity) {
            sockRepository.delete(sock);
        } else {
            sock.setQuantity(sock.getQuantity() - quantity);
            sockRepository.save(sock);
        }
    }
}
