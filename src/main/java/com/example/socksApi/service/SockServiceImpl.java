package com.example.socksApi.service;

import com.example.socksApi.dao.SockRepository;
import com.example.socksApi.dto.IncomeDto;
import com.example.socksApi.dto.OutcomeDto;
import com.example.socksApi.exceptions.ApiInvalidParametersException;
import com.example.socksApi.model.Operation;
import com.example.socksApi.model.Sock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SockServiceImpl implements SockService {

    private final static Logger LOG = Logger.getLogger(SockServiceImpl.class.getCanonicalName());

    private final SockRepository sockRepository;

    @Override
    public void income(IncomeDto dto) {

        if (dto.getCottonPart() >= 0 && dto.getCottonPart() <= 100 && dto.getAmount() >= 0) {
            Sock sock = new Sock(dto);
            try {

                Optional<Sock> sockOptional =
                        sockRepository.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

                if (sockOptional.isPresent()) {

                    Sock getSock = sockOptional.get();

                    getSock.setAmount(getSock.getAmount() + sock.getAmount());

                } else {
                    sockRepository.save(sock);
                }
            } catch (Exception e) {
                throw new ApiInvalidParametersException("Request parameters are missing or not in the correct format.");
            }
        } else {
            throw new ApiInvalidParametersException("Request parameters are missing or not in the correct format.");
        }
    }

    @Override
    public void outcome(OutcomeDto dto) {
        Sock sock = new Sock(dto);
        try {

            Optional<Sock> findSocksOpt = sockRepository
                    .findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

            if (findSocksOpt.isPresent()) {

                Sock findSock = findSocksOpt.get();

                if (sock.getAmount() <= findSock.getAmount()) {

                    findSock.setAmount(findSock.getAmount() - sock.getAmount());
                    sockRepository.save(findSock);
                }
                else {
                    throw new ApiInvalidParametersException("Incorrect number of pairs.");
                }
            }
        } catch (Exception e) {
            throw new ApiInvalidParametersException("Request parameters are missing or not in the correct format.");
        }
    }

    @Override
    public int count(String color, String operation, int cottonPart) {
        Optional<Integer> countOpt = Optional.empty();
        if (cottonPart >= 0 && cottonPart <= 100) {

            try {
                if (Operation.valueOf(operation) == Operation.equal) {

                    countOpt = sockRepository.findCountEqual(cottonPart, color.toLowerCase());

                } else if (Operation.valueOf(operation) == Operation.moreThan) {

                    countOpt = sockRepository.findCountMoreThan(cottonPart, color.toLowerCase());

                } else if (Operation.valueOf(operation) == Operation.lessThan) {

                    countOpt = sockRepository.findCountLessThan(cottonPart, color.toLowerCase());
                }
            } catch (IllegalArgumentException ex) {
                throw new ApiInvalidParametersException("Invalid comparison operation.");
            }

        } else {
            throw new ApiInvalidParametersException("Request parameters are missing or not in the correct format.");
        }
        return countOpt.orElse(0);
    }

}
