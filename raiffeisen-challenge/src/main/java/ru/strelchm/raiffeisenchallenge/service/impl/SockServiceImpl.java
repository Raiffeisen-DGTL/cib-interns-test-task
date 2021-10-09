package ru.strelchm.raiffeisenchallenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.digital_mind.marketplace.domain.User;
import ru.digital_mind.marketplace.domain.UserAppRole;
import ru.digital_mind.marketplace.domain.UserStatus;
import ru.digital_mind.marketplace.exception.AccessDeniedException;
import ru.digital_mind.marketplace.exception.BadRequestException;
import ru.digital_mind.marketplace.exception.NotFoundException;
import ru.digital_mind.marketplace.repo.UserRepository;
import ru.digital_mind.marketplace.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SockServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public SockServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getSelf(User user) {
        return getById(user.getId());
    }

    @Override
    public User getById(UUID id) {
        return getUserById(id);
    }

    @Override
    public UUID add(User user) {
        if (user.getUserAppRole() == null) {
            user.setUserAppRole(UserAppRole.CLIENT);
        } else if (user.getUserAppRole() == UserAppRole.ADMIN) {
            user.setUserAppRole(UserAppRole.ADMIN);
//            throw new BadRequestException("Can't register admin during standard registration attempt");
        }
//        user.setPassword(encoder.encode(user.getPassword())); todo
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user).getId();
    }

    @Override
    public User edit(User dto, User userDto) {
        if (!dto.getId().equals(userDto.getId()) && userDto.getUserAppRole() != UserAppRole.ADMIN) { // редактировать можно только самому себя или админу
            throw new AccessDeniedException();
        }

        User user = getUserById(dto.getId());

        if (dto.getLogin() != null && !user.getLogin().equals(dto.getLogin())) {
            user.setLogin(dto.getLogin());
        }

        if (dto.getStatus() != null && !user.getStatus().equals(dto.getStatus())) {
            user.setStatus(dto.getStatus());

//            if (dto.getStatus() == UserStatus.GLOBAL_BLOCKED) {
//                blockedUserRepository.save(new BlockedUser(dto.getId()));
//            }
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id, User user) {
        User deleteUser = getUserById(id);

        if (!id.equals(user.getId()) && user.getUserAppRole() != UserAppRole.ADMIN) { // удалить можно только самому себя или админу
            throw new AccessDeniedException();
        }

        userRepository.delete(deleteUser);
    }

    @Override
    public void blockUser(UUID userId, User user) {
        User blockUser = getUserById(userId);

        if (blockUser.getUserAppRole() != UserAppRole.ADMIN) {
            throw new AccessDeniedException("Only admin can block users");
        }
        changeUserStatusByAdmin(userId, blockUser, UserStatus.GLOBAL_BLOCKED);
//        blockedUserRepository.save(new BlockedUser(userId));
    }

    @Override
    public void unblockUser(UUID userId, User user) {
        User unblockUser = getUserById(userId);

        if (unblockUser.getUserAppRole() != UserAppRole.ADMIN) {
            throw new AccessDeniedException("Only admin can unblock users");
        }
        changeUserStatusByAdmin(userId, unblockUser, UserStatus.ACTIVE);
    }

    private void changeUserStatusByAdmin(UUID userId, User user, UserStatus status) {
        User changedUser = getUserById(userId);

        if (changedUser.getStatus() == status) {
            throw new BadRequestException("Status is already " + status);
        }

        user.setStatus(status);
        userRepository.save(changedUser);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("User with login " + login + " not found"));
        return Optional.ofNullable(user);
    }
}
