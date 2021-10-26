package ru.raiffeisen.socks.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socks.data.entity.Color;
import ru.raiffeisen.socks.data.entity.Socks;

import java.util.Optional;

@Repository
public interface SocksRepository extends CrudRepository<Socks, Long> {

    Optional<Socks> findByCottonPartAndColorName(int cottonPart , String color);

}