package ru.khromov.raiftask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khromov.raiftask.DAO.Sock;
import ru.khromov.raiftask.repository.SocksRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RaifService {

    private final SocksRepository socksRepository;

    @Autowired
    public RaifService(SocksRepository jpaRepository) {
        this.socksRepository = jpaRepository;
    }


    public List<Sock> showBySocks() {
        return socksRepository.findAllBy();
    }

    public int getSocks(String color, String operation, int cottonPart) {
        int num = 0;
        List<Sock> socks = this.socksRepository.findByColor(color);
        switch (operation.toLowerCase()) {
            case "morethan":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() > cottonPart) num += sock.getQuantity();
                }
                break;
            case "lessthan":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() < cottonPart) num += sock.getQuantity();
                }
                break;
            case "equals":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() == cottonPart) num += sock.getQuantity();
                }
                break;
            default:
                throw new IllegalArgumentException("Uncorrect operation, you must choose moreThan, lessThan or equals");
        }
        return num;
    }

    public void income(Sock sock) {
        if (socksRepository.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isEmpty()) {
            socksRepository.save(sock);
        } else {
            Sock sockInBd = socksRepository.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            sockInBd.setQuantity(sock.getQuantity() + sockInBd.getQuantity());
            socksRepository.save(sock);
        }

    }

    public void outcome(Sock sock) {
        if (socksRepository.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isPresent()) {
            Sock sockInBd = socksRepository.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            sockInBd.setQuantity(sock.getQuantity() - sockInBd.getQuantity());
            socksRepository.save(sock);
        }

    }
}
