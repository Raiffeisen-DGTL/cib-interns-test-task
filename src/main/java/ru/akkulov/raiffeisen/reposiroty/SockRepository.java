package ru.akkulov.raiffeisen.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akkulov.raiffeisen.model.Sock;

import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {

    /**
     * Возвращает список партий носков {@link Sock} по цвету
     *
     * @param color цвет
     *
     * @return List {@link Sock}
     */
    List<Sock> findAllByColor(String color);

    /**
     * Возвращает партию носков {@link Sock} по цвету и содержанию хлопка
     *
     * @param color цвет
     * @param cottonPart содержание хлопка
     *
     * @return {@link Sock}
     */
    Sock findSockByColorAndCottonPart(String color, int cottonPart);
}
