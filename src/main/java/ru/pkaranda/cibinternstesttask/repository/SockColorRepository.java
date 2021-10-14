package ru.pkaranda.cibinternstesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pkaranda.cibinternstesttask.model.domain.SockColor;

import java.util.Optional;

@Repository
public interface SockColorRepository extends JpaRepository<SockColor, Long> {

    Optional<SockColor> getSockColorById(Long id);
}
