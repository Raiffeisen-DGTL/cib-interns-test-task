package service.services;

import lombok.NonNull;
import model.enums.Operation;
import model.models.Sock;

import java.util.List;
import java.util.Optional;

public interface SockService {

    void save(@NonNull Sock sock);

    void delete(@NonNull Sock sock);

    int countSocks(@NonNull String color, @NonNull int cottonPart, @NonNull Operation operation);

    void addSocks(@NonNull Sock sock);

    void subtractSocks(@NonNull Sock sock);

    List<Sock> findByColorAndCottonPartGreaterThan(@NonNull String color, @NonNull int cottonPart);

    List<Sock> findByColorAndCottonPartLessThan(@NonNull String color,@NonNull int cottonPart);

    Optional<Sock> findByColorAndCottonPartEquals(@NonNull String color,@NonNull int cottonPart);
}
