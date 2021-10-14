package com.andidu.socks_server;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class SocksDao {
    @PersistenceContext
    private EntityManager entityManager;

    public static final int INCORRECT_ARGUMENT = -1;
    public static final int CORRECT_ARGUMENT = 1;

    public void addSocks(Socks sock) {
        List<SocksDB> socksDBS = getSocks(sock.getColor(), sock.getCottonPart());
        if (socksDBS == null || socksDBS.isEmpty()) {
            Query query = entityManager.createNativeQuery("insert into SocksDB (color, cotton_part, quantity) values (?1, ?2, ?3)")
                    .setParameter(1, sock.getColor())
                    .setParameter(2, sock.getCottonPart())
                    .setParameter(3, sock.getQuantity());
            query.executeUpdate();
        } else {
            SocksDB socksDB = socksDBS.get(0);
            entityManager.createNativeQuery("update socksdb set quantity = ?1 where id = ?2")
                    .setParameter(1, sock.getQuantity() + socksDB.getQuantity()).setParameter(2, socksDB.getId()).executeUpdate();
        }
    }

    public int subtractSocks(Socks sock) {
        List<SocksDB> socksDBS = getSocks(sock.getColor(), sock.getCottonPart());
        if (socksDBS == null || socksDBS.isEmpty()) {
            return INCORRECT_ARGUMENT;
        } else {
            SocksDB socksDB = socksDBS.get(0);
            int newVal = socksDB.getQuantity() - sock.getQuantity();
            if (newVal < 0) return INCORRECT_ARGUMENT;
            entityManager.createNativeQuery("update socksdb set quantity = ?1 where id = ?2")
                    .setParameter(1, newVal).setParameter(2, socksDB.getId()).executeUpdate();
        }
        return CORRECT_ARGUMENT;
    }

    private List<SocksDB> getSocks(String color, Integer cottonPart) {
        return entityManager.createQuery("from SocksDB where color = ?1 and cotton_part = ?2", SocksDB.class).
                setParameter(1, color).setParameter(2, cottonPart).getResultList();
    }

    public List<Integer> getSocks(String color, String operation, Integer cottonPart) {
        switch (operation) {
            case "moreThan":
                return entityManager.createQuery("select quantity from SocksDB where color = ?1 and cotton_part > ?2", Integer.class).
                        setParameter(1, color).setParameter(2, cottonPart).getResultList();
            case "lessThan":
                return entityManager.createQuery("select quantity from SocksDB where color = ?1 and cotton_part < ?2", Integer.class).
                        setParameter(1, color).setParameter(2, cottonPart).getResultList();
            case "equal":
                return entityManager.createQuery("select quantity from SocksDB where color = ?1 and cotton_part = ?2", Integer.class).
                        setParameter(1, color).setParameter(2, cottonPart).getResultList();
        }
        return null;
    }
}
