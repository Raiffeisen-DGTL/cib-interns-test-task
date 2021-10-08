package com.raiffeizen.demo.dao;

import com.raiffeizen.demo.models.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class SocksDao {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Long> findByColorWithMoreThanCottonPart (String color, int cottonPart) {
        return Optional.of(Long.parseLong(entityManager.createQuery(
                        "select sum(s.quantity) from Socks s where s.color LIKE :socksColor AND s.cottonPart > : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getSingleResult().toString()));
    }

    public Optional<Long>  findByColorWithLessThanCottonPart(String color, int cottonPart) {
        return Optional.of(Long.parseLong(entityManager.createQuery(
                        "select sum(s.quantity) from Socks s where s.color LIKE :socksColor AND s.cottonPart < : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getSingleResult().toString()));
    }

    public Optional<Long> findByColorWithEqualCottonPart(String color, int cottonPart) {
        return Optional.of(Long.parseLong(entityManager.createQuery(
                        "select sum(s.quantity) from Socks s where s.color LIKE :socksColor AND s.cottonPart = : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                        .
                .getSingleResult().toString()));
    }

    public void update(Socks socks) {
        entityManager.merge(socks);
    }


    public void save(Socks socks) {
        entityManager.persist(socks);
    }
}
