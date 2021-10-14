package com.rf.accountingforsocks.repository;

import com.rf.accountingforsocks.entity.Color;
import com.rf.accountingforsocks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SocksRepository extends JpaRepository<Socks, UUID> {
    List<Socks> findSocksByColorAndCottonPartGreaterThan(Color color, Integer cottonPart);
    List<Socks> findSocksByColorAndCottonPartLessThan(Color color, Integer cottonPart);
    List<Socks> findSocksByColorAndCottonPartEquals(Color color, Integer cottonPart);
    Optional<Socks> findSocksByColorAndCottonPart(Color color, Integer cottonPart);
}
