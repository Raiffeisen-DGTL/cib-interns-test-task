package com.ziborov.raifproject.repository;

import com.ziborov.raifproject.model.Socks;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SocksRepository extends JpaRepository<Socks, UUID> {

    List<Socks> findAllByColorAndCottonPart(Socks.SocksColor socksColor, Integer cottonPart, Pageable pageable);

    long countByColorAndCottonPartLessThan(Socks.SocksColor socksColor, Integer cottonPart);

    long countByColorAndCottonPartGreaterThan(Socks.SocksColor socksColor, Integer cottonPart);

    long countByColorAndCottonPart(Socks.SocksColor socksColor, Integer cottonPart);

}