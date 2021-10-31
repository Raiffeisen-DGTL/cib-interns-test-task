package socks_accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socks_accounting.model.Sock;
import socks_accounting.model.SockId;
import socks_accounting.payload.Operation;
import socks_accounting.repository.SockRepository;

import java.util.Optional;

/**
 * Requests serving layer.
 */
@Service
public class SockService {
    private SockRepository sockRepository;

    @Autowired
    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    /**
     * Method for adding socks.
     * @param request Sock's color, cottonPart and quantity to add
     */
    public void addSocks(Sock request) {
        Optional<Sock> sock = sockRepository.findById(
                new SockId(request.getColor(), request.getCottonPart())
        );

        if (sock.isPresent()) {
            // if we already have the row
            // with the same color and cottonPart as in request
            // then we should just update its quantity column
            int quantity = sock.get().getQuantity() + request.getQuantity();
            sock.get().setQuantity(quantity);

            sockRepository.save(sock.get());
        } else {
            // else we should insert the new row into the table
            sockRepository.save(request);
        }
    }

    /**
     * Method for deleting socks.
     * @param request Sock's color, cottonPart and quantity to delete
     */
    public void deleteSocks(Sock request) {
        Optional<Sock> sock = sockRepository.findById(
                new SockId(request.getColor(), request.getCottonPart())
        );

        if (sock.isPresent()) {
            int quantity = sock.get().getQuantity() - request.getQuantity();

            if (quantity > 0) {
                // if quantity is still > 0
                // then we should just update quantity column
                sock.get().setQuantity(quantity);
                sockRepository.save(sock.get());
            } else {
                // else we should delete the row
                sockRepository.deleteById(
                        new SockId(request.getColor(), request.getCottonPart())
                );
            }
        }

        /*
           If we haven't the row with the same color and cottonPart as in
           the request then we will do nothing. It's not an error.
         */
    }

    /**
     * Method allows getting quantity of socks with color @color
     * and with cottonPart less than, more than or equal to @cottonPart
     * @param color Must be non-empty String
     * @param operation Must be one of the following: MORETHAN, LESSTHAN, EQUAL
     * @param cottonPart Must be Integer >= 0 and <= 100
     * @return Quantity of socks
     */
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
