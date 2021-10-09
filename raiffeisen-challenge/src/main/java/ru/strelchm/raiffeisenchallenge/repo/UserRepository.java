package ru.strelchm.raiffeisenchallenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digital_mind.marketplace.domain.User;
import ru.digital_mind.marketplace.domain.UserStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SockRepository extends JpaRepository<Sock, UUID> {
    Optional<User> findByLogin(String login);

    List<User> findAllByStatus(UserStatus status);
}