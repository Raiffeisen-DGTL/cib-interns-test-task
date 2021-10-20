package com.raiffeisen.test.services;

import com.raiffeisen.test.dtos.SockBatchDto;
import com.raiffeisen.test.entities.SockBatch;
import com.raiffeisen.test.exceptions.InsufficientQuantityException;
import com.raiffeisen.test.exceptions.InvalidParamsException;
import com.raiffeisen.test.exceptions.NoSuchOperationException;
import com.raiffeisen.test.exceptions.ResourceNotFoundException;
import com.raiffeisen.test.repositories.ISockBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SockBatchService {

    private final String EXCEPTION_MESSAGE_NOT_FOUND = "No sock with requested params found.";
    private final String EXCEPTION_MESSAGE_BAD_OPERATION = "Bad operation request";
    private final String EXCEPTION_MESSAGE_BAD_PARAMS = "Bad params set";
    private final String EXCEPTION_MESSAGE_BAD_QUANTITY = "Requested quantity is higher than the quantity that we have in storage";

    private final ISockBatchRepository repository;

    public SockBatch save(String color, int cottonPart, int quantity) {
        if(cottonPart < 0 || cottonPart > 100 || quantity < 0) {
            throw new InvalidParamsException(EXCEPTION_MESSAGE_BAD_PARAMS);
        }
        if (repository.findByColorAndCottonPart(color, cottonPart).isEmpty()) {
            return repository.save(new SockBatch(color, cottonPart, quantity));
        }
        Optional<SockBatch> sock = repository.findByColorAndCottonPart(color, cottonPart);
        repository.updateSockQuantity(sock.get().getQuantity() + quantity, color, cottonPart);
        return repository.findByColorAndCottonPart(color, cottonPart).orElseThrow();
    }

    public void delete(String color, int cottonPart, int quantity) {
        if (repository.findByColorAndCottonPart(color, cottonPart).isPresent()) {
            SockBatch sockBatch = repository.findByColorAndCottonPart(color, cottonPart).get();
            if(sockBatch.getQuantity() == quantity) {
                repository.delete(sockBatch);
            }
            else if(sockBatch.getQuantity() > quantity) {
                repository.updateSockQuantity(sockBatch.getQuantity() - quantity, color, cottonPart);
            }
            else {
                throw new InsufficientQuantityException(EXCEPTION_MESSAGE_BAD_QUANTITY);
            }
            return;
        }
        throw new ResourceNotFoundException(EXCEPTION_MESSAGE_NOT_FOUND);
    }

    public List<SockBatchDto> findByColorAndOperationAndCottonPart(String color, String operation, int cottonPart) {
        if (cottonPart > 100 || cottonPart < 0) {
            throw new InvalidParamsException(EXCEPTION_MESSAGE_BAD_PARAMS);
        }
        if(color.equals("all")) {
            return findAllByOperationAndCottonPart(operation, cottonPart);
        }
            if (operation.equals("lessThan")) {
                List<SockBatchDto> dtos = repository.findByColorAndCottonPartLessThan(color, cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            if (operation.equals("greaterThan")) {
                List<SockBatchDto> dtos = repository.findByColorAndCottonPartGreaterThan(color, cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            if (operation.equals("equal")) {
                List<SockBatchDto> dtos = repository.findByColorAndCottonPartEquals(color, cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            throw new NoSuchOperationException(EXCEPTION_MESSAGE_BAD_OPERATION);
        }

        private List<SockBatchDto> findAllByOperationAndCottonPart(String operation, int cottonPart) {
            if(operation.equals("lessThan")) {
                List<SockBatchDto> dtos = repository.findAllByCottonPartLessThan(cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            if(operation.equals("greaterThan")) {
                List<SockBatchDto> dtos = repository.findAllByCottonPartGreaterThan(cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            if(operation.equals("equal")) {
                List<SockBatchDto> dtos = repository.findAllByCottonPartEquals(cottonPart).stream().map(SockBatchDto::new).collect(Collectors.toList());
                checkList(dtos);
                return dtos;
            }
            throw new NoSuchOperationException(EXCEPTION_MESSAGE_BAD_OPERATION);
        }

        private void checkList(List<SockBatchDto> dtos) {
            if(dtos.isEmpty()) {
                throw new ResourceNotFoundException(EXCEPTION_MESSAGE_NOT_FOUND);
            }
        }
}
