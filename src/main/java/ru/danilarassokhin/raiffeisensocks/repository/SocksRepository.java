package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Sock;

@Repository
public interface SocksRepository extends JpaRepository<Sock, Long> {
}
