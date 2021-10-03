package alaev.dev.raiffeisentesttask.service;

import alaev.dev.raiffeisentesttask.domain.Sock;
import alaev.dev.raiffeisentesttask.exception.NotEnoughSocksException;
import alaev.dev.raiffeisentesttask.repository.SockRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SockService {

  private final SockRepository repository;

  @Autowired
  public SockService(SockRepository repository) {
    this.repository = repository;
  }

  public void addSock(String color, Integer cottonPart, Integer quantity) {
    Optional<Sock> optionalSock = repository.findSockByColorAndCottonPart(color, cottonPart);

    optionalSock.ifPresentOrElse(
        //is present
        sock -> {
          sock.setQuantity(sock.getQuantity() + quantity);
          repository.save(sock);
        },
        //is optional.empty
        () -> repository.save(new Sock(null, color, cottonPart, quantity))
    );
  }

  public void releaseSocks(String color, Integer cottonPart, Integer quantity)
      throws NotEnoughSocksException {

    Optional<Sock> sockOptional = repository.findSockByColorAndCottonPartAndQuantityIsLessThanEqual(
        color, cottonPart, quantity);

    Sock sock = sockOptional.orElseThrow(
        () -> new NotEnoughSocksException(String.valueOf(quantity)));

    sock.setQuantity(sock.getQuantity() - quantity);
    repository.save(sock);
  }
}
