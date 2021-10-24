package application.services.utils;

import model.models.Sock;

import java.util.List;
import java.util.Optional;

public class CountQuantity {

    public static int countQuantities(Optional<Sock> sock) {
        int amountSocks = 0;
        if (sock.isPresent()) {
            amountSocks = sock.get().getQuantity();
        }
        return amountSocks;
    }

    public static int countQuantities(List<Sock> socks) {
        int amountSocks = 0;
        if (socks != null & socks.size() > 0) {
            for (Sock sock: socks) {
                amountSocks += sock.getQuantity();
            }
        }
        return amountSocks;
    }
}
