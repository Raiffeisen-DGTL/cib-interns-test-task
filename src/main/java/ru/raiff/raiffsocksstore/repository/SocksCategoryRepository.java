package ru.raiff.raiffsocksstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiff.raiffsocksstore.entity.SocksCategory;

import java.util.UUID;

@Repository
public interface SocksCategoryRepository extends JpaRepository<SocksCategory, UUID> {

    boolean existsByColorAndCottonPart(String color, Short cottonPart);
}
