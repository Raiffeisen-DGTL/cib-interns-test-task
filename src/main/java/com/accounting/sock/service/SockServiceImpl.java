package com.accounting.sock.service;

import com.accounting.sock.entity.Sock;
import com.accounting.sock.repository.SockRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SockServiceImpl implements SockService {

    @Autowired
    private SockRepository sockRepository;

    @Override
    public void registerSockIncome(@NotNull Sock sock) {

        Sock warehouseSock = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

        if (warehouseSock == null) {
            sockRepository.save(sock);
        } else {
            int newQuantity = warehouseSock.getQuantity() + sock.getQuantity();
            warehouseSock.setQuantity(newQuantity);
            sockRepository.save(warehouseSock);
        }
    }

    @Override
    public boolean registerSockOutcome(@NotNull Sock sock) {

        Sock warehouseSock = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

        if (warehouseSock != null) {
            int newQuantity = warehouseSock.getQuantity() - sock.getQuantity();

            // Если носков достаточно для отгрузки
            if (newQuantity > 0) {
                warehouseSock.setQuantity(newQuantity);
                sockRepository.save(warehouseSock);
                return true;
            }
        }

        return false;
    }

    @Override
    public long getTotalSockQuantity(@NotNull String color, @NotNull String operation, @NotNull Integer cottonPart) {

        switch (operation) {
            case "moreThan":
                return sockRepository.getSockCountMoreThanCottonValue(color, cottonPart);
            case "lessThan":
                return sockRepository.getSockCountLessThanCottonValue(color, cottonPart);
            case "equal":
                return sockRepository.getSockCountEqualCottonValue(color, cottonPart);
            default:
                return -1;
        }
    }
}
