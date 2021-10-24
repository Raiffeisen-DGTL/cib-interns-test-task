package com.fulkin.sockApp.service;

import com.fulkin.sockApp.exception.BadRequestException;
import com.fulkin.sockApp.exception.InternalServerException;
import com.fulkin.sockApp.exception.NotFoundException;
import com.fulkin.sockApp.model.OperationSocks;
import com.fulkin.sockApp.model.Sock;
import com.fulkin.sockApp.repository.SockRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Fulkin
 * Created on 20.10.2021
 */
@Service
public class SockService {

    private final SockRepository sockRepository;

    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public List<Sock> getListSock(String color, String operation, int cottonPart) {
        try {
            return sockRepository.getFilterListSocks(color, OperationSocks.valueOf(operation), cottonPart);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    public void addSocks(Sock sock) throws InternalServerException {
        sockRepository.updateSocks(sock);
    }

    public void deleteSocks(Sock sock) throws BadRequestException, NotFoundException {
        sockRepository.removeSocks(sock);
    }
}
