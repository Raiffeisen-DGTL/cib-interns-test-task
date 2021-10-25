package com.example.task.Other;

import com.example.task.Other.Socks;
import com.example.task.Repository.Rep.SockDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SockDAOimpl implements SockDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Socks> findAll() {
        return em.createNamedQuery("Article.findAll", Socks.class).getResultList();
    }

    @Override
    public void save(Socks socks) {
        em.persist(socks);
    }

    @Override
    public void update(Socks socks) {
        em.merge(socks);
    }

    @Override
    public void delete(Socks socks) {
        em.remove(socks);
    }
}