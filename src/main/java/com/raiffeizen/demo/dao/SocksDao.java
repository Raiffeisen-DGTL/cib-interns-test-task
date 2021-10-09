package com.raiffeizen.demo.dao;

import com.raiffeizen.demo.models.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class SocksDao {

    @PersistenceContext
    EntityManager entityManager;

    public long findQuantityByColorWithMoreThanCottonPart (String color, int cottonPart) {
        return Long.parseLong(entityManager.createQuery(
                        "select COALESCE(sum(s.quantity), 0) from Socks s where s.color LIKE :socksColor AND s.cottonPart > : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getSingleResult().toString());
    }

    public long  findQuantityByColorWithLessThanCottonPart(String color, int cottonPart) {
        return Long.parseLong(entityManager.createQuery(
                        "select COALESCE(sum(s.quantity), 0) from Socks s where s.color LIKE :socksColor AND s.cottonPart < : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getSingleResult().toString());
    }

    public long findQuantityByColorWithEqualCottonPart(String color, int cottonPart) {
        return Long.parseLong(entityManager.createQuery(
                        "select COALESCE(sum(s.quantity), 0) from Socks s where s.color LIKE :socksColor AND s.cottonPart = : socksCottonPart")
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getSingleResult().toString());
    }

    public Optional<Socks> findByColorAndCottonPart(String color, int cottonPart) {
        return  entityManager.createQuery("select s from Socks s where s.color LIKE :socksColor AND s.cottonPart = : socksCottonPart", Socks.class)
                .setParameter("socksColor", color)
                .setParameter("socksCottonPart", cottonPart)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void delete(Socks socks) {
        entityManager.remove(socks);
    }

    public void save(Socks socks) {
        entityManager.persist(socks);
    }
}
