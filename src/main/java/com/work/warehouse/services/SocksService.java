package com.work.warehouse.services;

import com.work.warehouse.dto.SocksDTO;
import com.work.warehouse.entities.Socks;
import com.work.warehouse.repositoryies.SocksRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class SocksService {

    public static final Logger LOG = LoggerFactory.getLogger(SocksService.class);

    private final SocksRepository socksRepository;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public SocksService(SocksRepository socksRepository,EntityManagerFactory entityManagerFactory){
        this.socksRepository = socksRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    public Socks income(SocksDTO socksDTO) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Socks socks;
        if(socksDTO.getId() == null) {
            socks = new Socks();
            socks.setColor(String.valueOf(socksDTO.getColor()));
            socks.setCottonPart(socksDTO.getCottonPart());
            socks.setQuantity(socksDTO.getQuantity());
            socks = socksRepository.save(socks);
        }else {
            socks = session.get(Socks.class, socksDTO.getId());
            socks.setQuantity(socks.getQuantity() + socksDTO.getQuantity());
            session.update(socks);
        }

        transaction.commit();
        session.close();

        return socks;
    }

    public void delete(Long id){
        Optional<Socks> socks = socksRepository.findById(id);
        socks.ifPresent(socksRepository::delete);
    }

    public Socks outcome(SocksDTO socksDTO){
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Socks socks;
        socks = session.get(Socks.class, socksDTO.getId());
        socks.setQuantity(socks.getQuantity() - socksDTO.getQuantity());
        session.update(socks);
        transaction.commit();
        session.close();

        return socks;
    }

    public List<Socks> getAllSocksByColor(String color){

        List<Socks> foundSocks = socksRepository.findSocksByColor(color);

        return foundSocks;
    }
}
