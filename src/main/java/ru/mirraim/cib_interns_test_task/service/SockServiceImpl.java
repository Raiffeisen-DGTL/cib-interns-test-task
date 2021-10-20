package ru.mirraim.cib_interns_test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirraim.cib_interns_test_task.dao.SockRepository;
import ru.mirraim.cib_interns_test_task.entity.Sock;

import java.util.ArrayList;
import java.util.List;

@Service
public class SockServiceImpl implements SockService {
    private SockRepository store;

    @Autowired
    public SockServiceImpl(SockRepository store) {
        this.store = store;
    }

    @Override
    @Transactional
    public Sock income(Sock sock) {

        Sock saved = store.findFirstByColorAndCottonPart(
                sock.getColor(), sock.getCottonPart()
        );
        if (saved == null) {
            store.save(sock);
            return sock;
        }
        int quantity = saved.getQuantity() + sock.getQuantity();
        saved.setQuantity(quantity);
        return saved;
    }

    @Override
    @Transactional
    public Integer findSocks(String color, String operation, int cottonPart) {
        List<Sock> socks = new ArrayList<>();
        if (operation.equals("equal")) {
           socks = store.findByColorAndCottonPart(color, cottonPart);
        }
        if (operation.equals("moreThan")) {
            socks = store.findByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        if (operation.equals("lessThan")) {
            socks = store.findByColorAndCottonPartLessThan(color, cottonPart);
        }
        return socks.stream().map(Sock::getQuantity).reduce(0, Integer::sum);
    }

    @Override
    @Transactional
    public Sock outcome(Sock sock) {
        Sock saved = store.findFirstByColorAndCottonPart(
                sock.getColor(), sock.getCottonPart()
        );
        if (saved == null) {
            store.save(sock);
            return null;
        }
        int quantity = saved.getQuantity() - sock.getQuantity();
        if (quantity < 0) {
            System.out.println("на складе недостаточно позиций для совершения операции");
        }
        saved.setQuantity(quantity);
        return saved;
    }
}
