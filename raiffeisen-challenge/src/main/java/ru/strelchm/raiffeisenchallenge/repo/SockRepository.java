package ru.strelchm.raiffeisenchallenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.domain.SockColor;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface SockRepository extends JpaRepository<Sock, UUID>, JpaSpecificationExecutor<Sock> {
    Optional<Sock> findByColorAndAndCottonPart(SockColor color, Integer cottonPart);
}
