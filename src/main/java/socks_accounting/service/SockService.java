package socks_accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socks_accounting.model.Sock;
import socks_accounting.model.SockId;
import socks_accounting.payload.Operation;
import socks_accounting.repository.SockRepository;

import java.util.Optional;

@Service
public class SockService {
    private SockRepository sockRepository;

    @Autowired
    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public void addSocks(Sock request) {
        Optional<Sock> sock = sockRepository.findById(
                new SockId(request.getColor(), request.getCottonPart())
        );

        if (sock.isPresent()) {
            int quantity = sock.get().getQuantity() + request.getQuantity();
            sock.get().setQuantity(quantity);

            sockRepository.save(sock.get());
        } else {
            sockRepository.save(request);
        }
    }

    public void deleteSocks(Sock request) {
        Optional<Sock> sock = sockRepository.findById(
                new SockId(request.getColor(), request.getCottonPart())
        );

        if (sock.isPresent()) {
            int quantity = sock.get().getQuantity() - request.getQuantity();

            if (quantity > 0) {
                sock.get().setQuantity(quantity);
                sockRepository.save(sock.get());
            } else {
                sockRepository.deleteById(
                        new SockId(request.getColor(), request.getCottonPart())
                );
            }
        }
    }

    public Integer getSockQuantity(
            String color, Operation operation, int cottonPart
    ) {
        switch (operation) {
            case MORETHAN:
                return sockRepository.GetMoreThanCottonPartQuantity(
                        color, cottonPart
                );
            case LESSTHAN:
                return sockRepository.GetLessThanCottonPartQuantity(
                        color, cottonPart
                );
            case EQUAL:
                return sockRepository.GetWithCottonPartQuantity(
                        color, cottonPart
                );
            default:
                return 0;
        }
    }
}
