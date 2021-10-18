package socksRepositoryTestTask.service;

import org.springframework.stereotype.Service;
import socksRepositoryTestTask.controller.SockController;
import socksRepositoryTestTask.exception.MissingRequestedQuantityException;
import socksRepositoryTestTask.exception.NoSuchEntityOnDatabaseException;
import socksRepositoryTestTask.model.Color;
import socksRepositoryTestTask.model.Sock;
import socksRepositoryTestTask.model.SockDTO;
import socksRepositoryTestTask.repository.SockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    public SockServiceImpl(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Override
    public int findAndCountSocks(Color color, SockController.Operation operation, int cottonPart) {
        List<Sock> foundedSocks;
        switch (operation) {

            case equals:
                Optional<Sock> foundedSock = sockRepository.findByColorAndCottonPart(color, cottonPart);
                if (!foundedSock.isEmpty()) {
                    return foundedSock.get().getQuantity();
                }
                break;

            case lessThan:
                foundedSocks = sockRepository.findByColorAndCottonPartLessThan(color, cottonPart);
                if (!foundedSocks.isEmpty()) {
                    return countFoundedSocks(foundedSocks);
                }
                break;

            case moreThan:
                foundedSocks = sockRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                if (!foundedSocks.isEmpty()) {
                    return countFoundedSocks(foundedSocks);
                }
                break;
        }
        return 0;
    }

    //считает сумму полей quantity носков в переданном списке
    private int countFoundedSocks(List<Sock> foundedSocks) {
        int counter = 0;
        for (Sock sock : foundedSocks) {
            counter += sock.getQuantity();
        }
        return counter;
    }

    @Override
    public void income(SockDTO sockDTO) {
        Optional<Sock> foundedSock =
                sockRepository.findByColorAndCottonPart(sockDTO.getColor(), sockDTO.getCottonPart());
        if (foundedSock.isEmpty()) {
            sockRepository.save(sockDTO.toEntity());
        } else {
            addSockQuantity(foundedSock.get(), sockDTO.getQuantity());
            sockRepository.save(foundedSock.get());
        }
    }

    //добавляет количество носков на складе при поступлении
    private void addSockQuantity(Sock sock, int addedQuantity) {
        int sumQuantity = addedQuantity + sock.getQuantity();
        sock.setQuantity(sumQuantity);
    }

    @Override
    public void outcome(SockDTO sockDTO) {
        Optional<Sock> foundedSock =
                sockRepository.findByColorAndCottonPart(sockDTO.getColor(), sockDTO.getCottonPart());

        if (foundedSock.isEmpty()) {
            throw new NoSuchEntityOnDatabaseException();
        } else if (foundedSock.get().getQuantity() < sockDTO.getQuantity()) {
            throw new MissingRequestedQuantityException();
        } else {
            reduceSockQuantity(foundedSock.get(), sockDTO.getQuantity());
        }
    }

    //сокращает количество носков на складе при отгрузке
    private void reduceSockQuantity(Sock foundedSock, int reducedQuantity) {
        int reduceQuantity = foundedSock.getQuantity() - reducedQuantity;
        if (reduceQuantity == 0) {
            sockRepository.delete(foundedSock);
        } else {
            foundedSock.setQuantity(reduceQuantity);
            sockRepository.save(foundedSock);
        }
    }
}

