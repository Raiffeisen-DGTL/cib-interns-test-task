package ru.raiffeisen.dgtl.cib.intern.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.entity.SocksId;

import java.util.List;
import java.util.Optional;

@Repository
public
interface SocksRepository extends JpaRepository<Socks, SocksId> {

    Optional<Socks> findById(SocksId socksId);

    List<Socks> findAllByColorAndCottonPartLessThan(String color, Byte cottonPart);

    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, Byte cottonPart);

    List<Socks> findAllByColorAndCottonPartEquals(String color, Byte cottonPart);
}
