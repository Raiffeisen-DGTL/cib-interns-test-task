package ru.strelchm.raiffeisenchallenge.service;

import ru.digital_mind.marketplace.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAll();

    User getSelf(User user);

    User getById(UUID id);

    UUID add(User user);

    User edit(User editedUser, User user);

    void delete(UUID id, User user);

    User getUserById(UUID id);

    Optional<User> getUserByLogin(String login);

    void blockUser(UUID userId, User user);

    void unblockUser(UUID userId, User user);
}
