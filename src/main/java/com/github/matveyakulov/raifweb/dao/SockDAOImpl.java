package com.github.matveyakulov.raifweb.dao;

import com.github.matveyakulov.raifweb.entity.Sock;
import com.github.matveyakulov.raifweb.enums.Operation;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SockDAOImpl implements SockDAO {

    @Autowired
    private JpaDAO jpaDAO;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addSock(Sock sock) {

        Sock existSock = jpaDAO.findByColorAndAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (existSock != null) {
            existSock.setQuantity(existSock.getQuantity() + sock.getQuantity());
        }else{
            jpaDAO.save(sock);
        }
    }

    @Transactional
    @Override
    public void deleteSock(Sock sock) {
        Sock existSock = jpaDAO.findByColorAndAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (existSock != null && (existSock.getQuantity() - sock.getQuantity()) >= 0) {
            existSock.setQuantity(existSock.getQuantity() - sock.getQuantity());
        }

    }

    @Transactional
    @Override
    public int getAll(String color, Operation operation, int cottonPart) {

        Query executionQuery;
        switch (operation) {
            case MORETHAN: {
                String queryString = "from Sock where color=: color and cottonPart between :lCottonPart and 100";
                executionQuery = entityManager.createQuery(queryString);
                executionQuery.setParameter("color", color);
                executionQuery.setParameter("lCottonPart", cottonPart + 1);
                break;
            }

            case LESSTHAN: {
                String queryString = "from Sock where color=: color and cottonPart between 0 and :rCottonPart";
                executionQuery = entityManager.createQuery(queryString);
                executionQuery.setParameter("color", color);
                executionQuery.setParameter("rCottonPart", cottonPart - 1);
                break;
            }

            case EQUAL: {
                String queryString = "from Sock where color=: color and cottonPart =:cottonPart";
                executionQuery = entityManager.createQuery(queryString);
                executionQuery.setParameter("color", color);
                executionQuery.setParameter("cottonPart", cottonPart);
                break;
            }
            default: {
                return 0;
            }
        }
        List<Sock> socks = executionQuery.getResultList();
        int sum = 0;
        for(Sock s: socks){
            sum+= s.getQuantity();
        }
        return sum;
    }

}
