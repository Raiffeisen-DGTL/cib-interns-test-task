package application.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.enums.Operation;
import model.models.Sock;
import model.repositories.SockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.exceptions.CustomException;
import service.services.SockService;

import java.util.List;
import java.util.Optional;

import static application.services.utils.CheckCorrect.check;
import static application.services.utils.CheckCorrect.checkCottonPart;
import static application.services.utils.CountQuantity.countQuantities;

@Service
@RequiredArgsConstructor
public class SockServiceDefault implements SockService {

    private final SockRepository sockRepository;

    @Override
    @Transactional(readOnly = true)
    public int countSocks(@NonNull String color, @NonNull int cottonPart, @NonNull Operation operation) throws CustomException{
        switch (operation) {
            case moreThan: return countQuantities(findByColorAndCottonPartGreaterThan(color, cottonPart));
            case lessThan: return countQuantities(findByColorAndCottonPartLessThan(color, cottonPart));
            case equal: return countQuantities(findByColorAndCottonPartEquals(color, cottonPart));
            default: throw new CustomException("Operation is not correct", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void addSocks(@NonNull Sock sock) throws CustomException {
        check(sock);
        findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).
        ifPresentOrElse(
                foundSock -> {
                    foundSock.setQuantity(sock.getQuantity() + foundSock.getQuantity());
                    save(foundSock);
                },
                () -> save(sock)
        );
    }

    @Override
    @Transactional
    public void subtractSocks(@NonNull Sock sock) throws CustomException {
        check(sock);
        Sock foundSock= findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).
                orElseThrow( () -> new CustomException("<Sock> sock wasn't found with input params", HttpStatus.BAD_REQUEST));
        int diff = foundSock.getQuantity() - sock.getQuantity();
        if (diff > 0) {
            foundSock.setQuantity(diff);
            save(foundSock);
        } else if (diff == 0) {
            delete(foundSock);
        } else {
            throw new CustomException("Input quantity is over found sock quantity", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void delete(@NonNull Sock sock) {
        sockRepository.delete(sock);
    }

    @Override
    @Transactional
    public void save(@NonNull Sock sock) {
        sockRepository.save(sock);
    }

    @Override
    @Transactional
    public List<Sock> findByColorAndCottonPartGreaterThan(@NonNull String color, @NonNull int cottonPart) throws CustomException {
        checkCottonPart(cottonPart);
        return sockRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
    }

    @Override
    @Transactional
    public List<Sock> findByColorAndCottonPartLessThan(@NonNull String color, @NonNull int cottonPart) throws CustomException{
        checkCottonPart(cottonPart);
        return sockRepository.findByColorAndCottonPartLessThan(color, cottonPart);
    }

    @Override
    @Transactional
    public Optional<Sock> findByColorAndCottonPartEquals(@NonNull String color, @NonNull int cottonPart) throws CustomException{
        checkCottonPart(cottonPart);
        return sockRepository.findByColorAndCottonPartEquals(color, cottonPart);
    }

}
