package com.example.testsocks.repository;

import com.example.testsocks.model.Socks;
import com.example.testsocks.model.SocksPK;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocksRepositoryImpl implements SocksRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void income(Socks socks) {
        Session session = sessionFactory.getCurrentSession();
        SocksPK socksPK = new SocksPK(socks.getColor(), socks.getCottonPart());
        int currentQuantitySocks = getQuantity(socksPK);
        socks.setQuantity(currentQuantitySocks + socks.getQuantity());
        session.replicate(socks, ReplicationMode.OVERWRITE);
    }

    @Override
    public boolean outcome(Socks socks) {
        Session session = sessionFactory.getCurrentSession();
        SocksPK socksPK = new SocksPK(socks.getColor(), socks.getCottonPart());
        int currentQuantitySocks = getQuantity(socksPK);
        if (currentQuantitySocks > socks.getQuantity()) {
            socks.setQuantity(currentQuantitySocks - socks.getQuantity());
            session.replicate(socks, ReplicationMode.OVERWRITE);
        } else if (currentQuantitySocks == socks.getQuantity()) {
            session.delete(session.get(Socks.class, socksPK));
        } else return false;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int countMoreThan(SocksPK socksPK) {
        Session session = sessionFactory.getCurrentSession();
        List<Socks> socksList = session.createQuery("FROM Socks WHERE color = :color " +
                "AND cottonpart >= :cottonPart")
                .setParameter("color", socksPK.getColor())
                .setParameter("cottonPart", socksPK.getCottonPart())
                .list();
        return socksList.stream().mapToInt(Socks::getQuantity).sum();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int countLessThan(SocksPK socksPK) {
        Session session = sessionFactory.getCurrentSession();
        List<Socks> socksList = session.createQuery("FROM Socks WHERE color = :color " +
                "AND cottonpart <= :cottonPart")
                .setParameter("color", socksPK.getColor())
                .setParameter("cottonPart", socksPK.getCottonPart())
                .list();
        return socksList.stream().mapToInt(Socks::getQuantity).sum();
    }
    
    @Override
    public int getQuantity(SocksPK socksPK) {
        Session session = sessionFactory.getCurrentSession();
        Socks socks = session.get(Socks.class, socksPK);
        if (socks == null) {
            return 0;
        } else {
            return socks.getQuantity();
        }
    }
}
