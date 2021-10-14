package com.raif.storage.sock.service;

import com.raif.storage.exception.SockValidationException;
import com.raif.storage.sock.model.*;
import com.raif.storage.sock.validator.SockValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = SockValidationException.class)
@Slf4j
@AllArgsConstructor
public class SockServiceImpl implements SockService {

    private final SockRepository sockRepository;
    private final SockValidator sockValidator;
    private final SockMapper sockMapper;

    @Override
    public void createSockIncome(SockDto sock) {
        sockValidator.validateSockInPostRequest(sock);

        var storedOpt = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        var toStore = new Sock();

        if (storedOpt.isPresent()) {
            toStore = storedOpt.get();
            toStore.setQuantity(toStore.getQuantity() + sock.getQuantity());
        } else {
            toStore = sockMapper.toEntity(sock);
        }

        sockValidator.validateSockForSaving(toStore);
        sockRepository.save(toStore);
        log.info("Sock income created, result {{}}", toStore);
    }

    @Override
    public void createSockOutcome(SockDto sock) {
        sockValidator.validateSockInPostRequest(sock);

        var storedOpt = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        var toStore = new Sock();

        if (storedOpt.isPresent()) {
            toStore = storedOpt.get();
            toStore.setQuantity(toStore.getQuantity() - sock.getQuantity());
        } else {
            toStore = sockMapper.toEntity(sock);
        }

        sockValidator.validateSockForSaving(toStore);
        sockRepository.save(toStore);
        log.info("Sock outcome created, result {{}}", toStore);
    }

    @Override
    public long countSocks(String color, String operation, int cottonPart) {
        sockValidator.validateGetRequestForSockCount(color, operation, cottonPart);

        int count = 0;
        if ("moreThan".equals(operation)) {
            for (var sock : sockRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart)) {
                count += sock.getQuantity();
            }
        } else if ("lessThan".equals(operation)) {
            for (var sock : sockRepository.findAllByColorAndCottonPartLessThan(color, cottonPart)) {
                count += sock.getQuantity();
            }
        } else if ("equal".equals(operation)) {
            var storedOpt = sockRepository.findByColorAndCottonPart(color, cottonPart);
            return storedOpt.map(Sock::getQuantity).orElse(0);
        }

        log.info("Counted {{}} socks for request: color={{}}, operation={{}}, cottonPart={{}}",
                count, color, operation, cottonPart);

        return count;
    }
}
