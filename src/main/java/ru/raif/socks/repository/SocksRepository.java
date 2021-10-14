package ru.raif.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raif.socks.entity.Socks;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface SocksRepository extends JpaRepository<Socks,Long> {

    Optional<Socks> findSocksByColorAndCottonPart(String color, Integer cottonPart);

    Stream<Socks> streamSocksByColor(String color);
}
