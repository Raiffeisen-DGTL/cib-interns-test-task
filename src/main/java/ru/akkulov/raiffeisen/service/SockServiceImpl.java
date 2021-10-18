package ru.akkulov.raiffeisen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akkulov.raiffeisen.exception.SockIncorrectDataException;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.reposiroty.SockRepository;
import ru.akkulov.raiffeisen.util.Operation;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    @Override
    public Sock createSock(Sock comingSock) {
        var comingSockColor = comingSock.getColor();
        var comingSockCottonPart = comingSock.getCottonPart();
        var comingSockQuantity = comingSock.getQuantity();
        var currentSock = sockRepository.findSockByColorAndCottonPart(comingSockColor, comingSockCottonPart);

        if (comingSock.getCottonPart() >= 0 && comingSock.getCottonPart() <= 100 && comingSock.getQuantity() > 0) {
            if (currentSock == null) {
                return sockRepository.save(comingSock);
            }
            int newQuantity = currentSock.getQuantity() + comingSockQuantity;
            currentSock.setQuantity(newQuantity);
            return sockRepository.save(currentSock);
        }

        throw new SockIncorrectDataException("cottonPart value must be between 0-100, " +
                "quantity value must be > 0");
    }

    @Override
    public Sock getSockByColorAndCottonPart(Sock comingSock) {
        var comingSockColor = comingSock.getColor();
        var comingSockCottonPart = comingSock.getCottonPart();
        var comingSockQuantity = comingSock.getQuantity();
        var currentSock = sockRepository.findSockByColorAndCottonPart(comingSockColor, comingSockCottonPart);

        if (currentSock == null) {
            throw new SockIncorrectDataException(
                    "We apologize for the inconvenience, " +
                            "socks with this cottonPart value is not available in our stock right now");
        }

        if (comingSock.getCottonPart() >= 0 && comingSock.getCottonPart() <= 100 && comingSock.getQuantity() > 0) {
            if (currentSock.getQuantity() >= comingSockQuantity) {
                int newQuantity = currentSock.getQuantity() - comingSockQuantity;
                currentSock.setQuantity(newQuantity);
            } else {
                throw new SockIncorrectDataException
                        (String.format("We apologize for the inconvenience, " +
                                        "there are only %s pairs of socks in store warehouse right now, " +
                                        "please select fewer pairs than you need",
                                currentSock.getQuantity()));
            }
        }

        return sockRepository.save(currentSock);
    }

    public String getQuantityByParameters(String color, Operation operation, int cottonPart) {
        var socks = sockRepository.findAllByColor(color);

        switch (operation) {

            case moreThan:
                return socks.stream()
                        .filter(sock -> sock.getCottonPart() > cottonPart)
                        .map(Sock::getQuantity)
                        .reduce(0, Integer::sum)
                        .toString();
            case lessThan:
                return socks.stream()
                        .filter(sock -> sock.getCottonPart() < cottonPart)
                        .map(Sock::getQuantity)
                        .reduce(0, Integer::sum)
                        .toString();
            case equal:
                return socks.stream()
                        .filter(sock -> sock.getCottonPart() == cottonPart)
                        .map(Sock::getQuantity)
                        .reduce(0, Integer::sum)
                        .toString();
            default:
                return "No socks with this parameters";
        }
    }

    @Override
    public Sock getSocksById(long sockId) {
        return sockRepository.findById(sockId)
                .orElseThrow(() -> new SockIncorrectDataException(String.format(
                        "Sock with id: %s is not found!", sockId)));
    }

    @Override
    public void deleteSocksById(long sockId) {
        sockRepository.deleteById(sockId);
    }

    @Override
    public List<Sock> getAllSocks() {
        return sockRepository.findAll();
    }
}
