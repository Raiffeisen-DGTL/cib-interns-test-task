package ru.vasiliev.socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends CrudRepository<Socks,Integer> {
    List<Socks> findAll();
}
